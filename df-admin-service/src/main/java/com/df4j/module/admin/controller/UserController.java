package com.df4j.module.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.df4j.base.server.Result;
import com.df4j.base.utils.MapUtils;
import com.df4j.base.utils.FieldUtils;
import com.df4j.base.utils.DateUtils;
import com.df4j.base.form.Field;
import com.df4j.base.form.BoundType;
import com.df4j.base.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.df4j.module.admin.model.User;
import com.df4j.module.admin.service.UserService;


//@RestController
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
        //Long id = MapUtils.getLongFromMap(map, "id", null);
        Field<Long> id = FieldUtils.getLongField(map, "id", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //String userName = MapUtils.getStringFromMap(map, "userName", null);
        Field<String> userName = FieldUtils.getStringField(map, "userName", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //String nickName = MapUtils.getStringFromMap(map, "nickName", null);
        Field<String> nickName = FieldUtils.getStringField(map, "nickName", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //String mobileNo = MapUtils.getStringFromMap(map, "mobileNo", null);
        Field<String> mobileNo = FieldUtils.getStringField(map, "mobileNo", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //String headUrl = MapUtils.getStringFromMap(map, "headUrl", null);
        Field<String> headUrl = FieldUtils.getStringField(map, "headUrl", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //Integer userState = MapUtils.getIntegerFromMap(map, "userState", null);
        Field<Integer> userState = FieldUtils.getIntegerField(map, "userState", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //String userPass = MapUtils.getStringFromMap(map, "userPass", null);
        Field<String> userPass = FieldUtils.getStringField(map, "userPass", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //String salt = MapUtils.getStringFromMap(map, "salt", null);
        Field<String> salt = FieldUtils.getStringField(map, "salt", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //String departmentCode = MapUtils.getStringFromMap(map, "departmentCode", null);
        Field<String> departmentCode = FieldUtils.getStringField(map, "departmentCode", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //Integer flag = MapUtils.getIntegerFromMap(map, "flag", null);
        Field<Integer> flag = FieldUtils.getIntegerField(map, "flag", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //Date createTime = MapUtils.getDateFromMap(map, "createTime", null);
        Field<Date> createTime = FieldUtils.getDateField(map, "createTime", DateUtils.DATE_TIME_PATTERN, false, BoundType.INCLUDE, BoundType.INCLUDE);
        //Date updateTime = MapUtils.getDateFromMap(map, "updateTime", null);
        Field<Date> updateTime = FieldUtils.getDateField(map, "updateTime", DateUtils.DATE_TIME_PATTERN, false, BoundType.INCLUDE, BoundType.INCLUDE);
        List<User> list = userService.list(id, userName, nickName, mobileNo, headUrl, userState, userPass, salt, departmentCode, flag, createTime, updateTime);
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
        String headUrl = MapUtils.getStringFromMap(map, "headUrl", null);
        Integer userState = MapUtils.getIntegerFromMap(map, "userState", null);
        String userPass = MapUtils.getStringFromMap(map, "userPass", null);
        String salt = MapUtils.getStringFromMap(map, "salt", null);
        String departmentCode = MapUtils.getStringFromMap(map, "departmentCode", null);
        Integer flag = MapUtils.getIntegerFromMap(map, "flag", null);
        User user = userService.add(userName, nickName, mobileNo, headUrl, userState, userPass, salt, departmentCode, flag);
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
        String headUrl = MapUtils.getStringFromMap(map, "headUrl", null);
        Integer userState = MapUtils.getIntegerFromMap(map, "userState", null);
        String userPass = MapUtils.getStringFromMap(map, "userPass", null);
        String salt = MapUtils.getStringFromMap(map, "salt", null);
        String departmentCode = MapUtils.getStringFromMap(map, "departmentCode", null);
        Integer flag = MapUtils.getIntegerFromMap(map, "flag", null);
        User user = userService.update(id, userName, nickName, mobileNo, headUrl, userState, userPass, salt, departmentCode, flag);
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
