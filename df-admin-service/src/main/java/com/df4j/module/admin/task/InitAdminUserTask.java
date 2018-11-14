package com.df4j.module.admin.task;

import com.df4j.base.utils.Md5Utils;
import com.df4j.base.utils.RandomUtils;
import com.df4j.base.utils.SpringUtils;
import com.df4j.base.utils.ValidateUtils;
import com.df4j.module.admin.model.Role;
import com.df4j.module.admin.model.User;
import com.df4j.module.admin.model.UserRole;
import com.df4j.module.admin.properties.InitAdminUserProperties;
import com.df4j.module.admin.properties.SecurityProperties;
import com.df4j.module.admin.service.RoleService;
import com.df4j.module.admin.service.UserRoleService;
import com.df4j.module.admin.service.UserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InitAdminUserTask implements Runnable{

    private Logger logger = LoggerFactory.getLogger(InitAdminUserTask.class);

    @Override
    public void run() {
        try {
            logger.info("开始初始化管理员用户的任务");
            InitAdminUserProperties initAdminUserProperties = SpringUtils.getBean(InitAdminUserProperties.class);
            if (!initAdminUserProperties.isEnable()) {
                logger.warn("未开启初始化管理员用户");
                return;
            }

            UserService userService = SpringUtils.getBean(UserService.class);
            User user = userService.listOne(initAdminUserProperties.getUsername());
            if (ValidateUtils.isNull(user)) {
                logger.warn("系统管理员用户不存在，正在初始化系统管理用户");
                SecurityProperties securityProperties = SpringUtils.getBean(SecurityProperties.class);
                String salt = Md5Utils.md5(RandomUtils.getStringCode(12));
                String pass = new SimpleHash(securityProperties.getAlgorithm(), initAdminUserProperties.getPassword(),
                        ByteSource.Util.bytes(salt), securityProperties.getHashIterations()).toHex();
                user = userService.add(initAdminUserProperties.getUsername(), "系统管理员", initAdminUserProperties.getMobileNo(),
                        null, 0, pass, salt, null, 0);
                logger.warn("系统管理员用户{}初始化完成", user.getUserName());
            }

            RoleService roleService = SpringUtils.getBean(RoleService.class);
            Role role = roleService.listOne("admin");
            if (ValidateUtils.isNull(role)) {
                logger.warn("系统管理员用户组不存在，正在初始化系统系统用户组");
                role = roleService.add("admin", "系统管理员", "系统管理员用户组", 0, 0);
            }
            Long userId = user.getId();
            Long roleId = role.getId();
            UserRoleService userRoleService = SpringUtils.getBean(UserRoleService.class);
            UserRole userRole = userRoleService.listOne(userId, roleId);
            if (ValidateUtils.isNull(userRole)) {
                logger.warn("默认系统管理员用户未加入管理组，正在将管理用户加入管理组");
                userRoleService.add(userId, roleId, 0);
            }
        }catch (Exception e){
            logger.error("初始化默认管理员用户出现异常", e);
        }
    }
}
