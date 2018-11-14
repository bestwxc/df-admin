package com.df4j.module.admin.controller;

import com.df4j.base.exception.DfException;
import com.df4j.base.server.Result;
import com.df4j.base.utils.*;
import com.df4j.module.admin.model.User;
import com.df4j.module.admin.properties.SecurityProperties;
import com.df4j.module.admin.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import org.apache.shiro.mgt.SecurityManager;

@RestController
@RequestMapping("/api/admin")
public class UserExtController extends UserController{

    private Logger logger = LoggerFactory.getLogger(UserExtController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityManager securityManager;

    @Autowired
    private SecurityProperties securityProperties;


    /**
     * 图形验证码接口
     * @param map
     * @return
     */
    @RequestMapping("/image/code")
    public Result imageCode(@RequestBody Map<String,?> map) {

        return ResultUtils.success(null);
    }


    /**
     *
     * @param map
     * @return
     */
    @RequestMapping("/user/login")
    public Result login(@RequestBody Map<String,?> map) {
        logger.info("securityManager:{}",securityManager);
        String userName = MapUtils.getStringFromMapNotNull(map, "userName");
        String password = MapUtils.getStringFromMapNotNull(map, "password");
        Subject currentUser = SecurityUtils.getSubject();
        if(!currentUser.isAuthenticated()){
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName, password);
            currentUser.login(usernamePasswordToken);
        }
        User user = userService.listOne(userName);
        return ResultUtils.success(user);
    }

    @RequestMapping("/user/logout")
    public Result logout(@RequestBody Map<String,?> map) {
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.logout();
        }catch (Exception e){
            logger.warn("调用登出报错，可能是未登录状态调用了登出");
        }
        return ResultUtils.success(null);
    }


    /**
     * 修改密码接口
     * @param map
     * @return
     */
    @RequestMapping("/user/passwd")
    public Result passwd(@RequestBody Map<String,?> map) {
        Long id = MapUtils.getLongFromMapNotNull(map, "id");
        String password = MapUtils.getStringFromMapNotNull(map, "password");
        String newPassword = MapUtils.getStringFromMapNotNull(map, "newPassword");
        String newPasswordCheck = MapUtils.getStringFromMapNotNull(map, "newPasswordCheck");
        User user = userService.listOne(id);
        if(!newPassword.equals(newPasswordCheck)){
            throw new DfException("两次输入的密码不一致");
        }
        if(ValidateUtils.isNull(user)){
            throw new DfException("用户不存在");
        }
        String salt = user.getSalt();
        if(!user.getUserPass().equals(this.mdPass(password, salt))){
            throw new DfException("原用户密码不正确");
        }
        salt = this.getSalt();
        String userPass = this.mdPass(newPassword, salt);
        userService.update(id, null, null, null, null, null, userPass, salt, null, null);
        return ResultUtils.success(null);
    }

    /**
     * 重置密码接口
     * @param map
     * @return
     */
    @RequestMapping("/user/resetpass")
    public Result resetpass(@RequestBody Map<String,?> map) {
        Long id = MapUtils.getLongFromMapNotNull(map, "id");
        String newPassword = MapUtils.getStringFromMapNotNull(map, "newPassword");
        String newPasswordCheck = MapUtils.getStringFromMapNotNull(map, "newPasswordCheck");
        User user = userService.listOne(id);
        if(!newPassword.equals(newPasswordCheck)){
            throw new DfException("两次输入的密码不一致");
        }
        if(ValidateUtils.isNull(user)){
            throw new DfException("用户不存在");
        }
        String salt = this.getSalt();
        String userPass = this.mdPass(newPassword, salt);
        userService.update(id, null, null, null, null, null, userPass, salt, null, null);
        return ResultUtils.success(null);
    }




    /**
     * 重写用户新增方法
     * @param map
     * @return
     */
    @Override
    @RequestMapping("/user/add")
    public Result add(@RequestBody Map<String,?> map){
        String salt = this.getSalt();
        Map<String,Object> temp = (Map<String,Object>) map;
        temp.put("salt",salt);
        String userPass = MapUtils.getStringFromMapNotNull(map, "userPass");
        userPass = this.mdPass(userPass, salt);
        temp.put("userPass", userPass);
        return super.add(map);
    }

    /**
     * 生成密码摘要
     * @return
     */
    private String getSalt(){
        String str = RandomUtils.getStringCode(12);
        return Md5Utils.md5(str);
    }

    /**
     * 随机生成salt
     * @param pass
     * @param salt
     * @return
     */
    private String mdPass(String pass, String salt){
        return new SimpleHash(securityProperties.getAlgorithm(), pass,
                ByteSource.Util.bytes(salt), securityProperties.getHashIterations()).toHex();
    }
}
