package com.df4j.module.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.df4j.base.server.Result;
import com.df4j.base.utils.MapUtils;
import com.df4j.base.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Map;
import com.df4j.module.admin.model.User;
import com.df4j.module.admin.service.UserService;


@RestController
@RequestMapping("/api/admin")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 查询
     * @param map
     * @return
     */
    @RequestMapping("/user/list")
    public Result<List<User>> list(@RequestBody Map<String,?> map){
        Long id = MapUtils.getLongFromMap(map, "id", null);
        String userName = MapUtils.getStringFromMap(map, "userName", null);
        String nickName = MapUtils.getStringFromMap(map, "nickName", null);
        String mobileNo = MapUtils.getStringFromMap(map, "mobileNo", null);
        Integer userState = MapUtils.getIntegerFromMap(map, "userState", null);
        String userPass = MapUtils.getStringFromMap(map, "userPass", null);
        String salt = MapUtils.getStringFromMap(map, "salt", null);
        String departmentCode = MapUtils.getStringFromMap(map, "departmentCode", null);
        Integer flag = MapUtils.getIntegerFromMap(map, "flag", null);
        List<User> list = userService.list(id, userName, nickName, mobileNo, userState, userPass, salt, departmentCode, flag, null, null);
        return ResultUtils.success(list);
    }

    /**
     * 新增
     * @param map
     * @return
     */
    @RequestMapping("/user/add")
    public Result<User> add(@RequestBody Map<String,?> map){
        String userName = MapUtils.getStringFromMap(map, "userName", null);
        String nickName = MapUtils.getStringFromMap(map, "nickName", null);
        String mobileNo = MapUtils.getStringFromMap(map, "mobileNo", null);
        Integer userState = MapUtils.getIntegerFromMap(map, "userState", null);
        String userPass = MapUtils.getStringFromMap(map, "userPass", null);
        String salt = MapUtils.getStringFromMap(map, "salt", null);
        String departmentCode = MapUtils.getStringFromMap(map, "departmentCode", null);
        Integer flag = MapUtils.getIntegerFromMap(map, "flag", null);
        User user = userService.add(userName, nickName, mobileNo, userState, userPass, salt, departmentCode, flag);
        return ResultUtils.success(user);
    }

    /**
     * 更新
     * @param map
     * @return
     */
    @RequestMapping("/user/update")
    public Result<User> update(@RequestBody Map<String,?> map) {
        Long id = MapUtils.getLongFromMapNotNull(map, "id");
        String userName = MapUtils.getStringFromMap(map, "userName", null);
        String nickName = MapUtils.getStringFromMap(map, "nickName", null);
        String mobileNo = MapUtils.getStringFromMap(map, "mobileNo", null);
        Integer userState = MapUtils.getIntegerFromMap(map, "userState", null);
        String userPass = MapUtils.getStringFromMap(map, "userPass", null);
        String salt = MapUtils.getStringFromMap(map, "salt", null);
        String departmentCode = MapUtils.getStringFromMap(map, "departmentCode", null);
        Integer flag = MapUtils.getIntegerFromMap(map, "flag", null);
        User user = userService.update(id, userName, nickName, mobileNo, userState, userPass, salt, departmentCode, flag);
        return ResultUtils.success(user);
    }


    /**
     * 删除
     * @param map
     * @return
     */
    @RequestMapping("/user/delete")
    public Result delete(@RequestBody Map<String,?> map) {
        Long id = MapUtils.getLongFromMapNotNull(map, "id");
        int num = userService.logicDelete(id);
        return ResultUtils.success(null);
    }
}
