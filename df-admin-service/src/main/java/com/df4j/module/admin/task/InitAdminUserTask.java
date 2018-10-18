package com.df4j.module.admin.task;

import com.df4j.base.utils.SpringUtils;
import com.df4j.base.utils.ValidateUtils;
import com.df4j.module.admin.model.User;
import com.df4j.module.admin.properties.InitAdminUserProperties;
import com.df4j.module.admin.service.RoleService;
import com.df4j.module.admin.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InitAdminUserTask implements Runnable{

    private Logger logger = LoggerFactory.getLogger(InitAdminUserTask.class);

    @Override
    public void run() {
        logger.info("开始初始化管理员用户");
        InitAdminUserProperties initAdminUserProperties = SpringUtils.getBean(InitAdminUserProperties.class);
        if(!initAdminUserProperties.isEnable()){
            logger.info("未开启初始化管理员用户");
            return;
        }
        UserService userService = SpringUtils.getBean(UserService.class);
        RoleService roleService = SpringUtils.getBean(RoleService.class);
        User user = userService.listOne(initAdminUserProperties.getUsername());
        if(ValidateUtils.isNull(user)){
            //userService.add()
        }
    }
}
