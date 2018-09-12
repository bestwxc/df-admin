package net.df.module.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import net.df.base.server.Result;
import net.df.base.utils.MapUtils;
import net.df.base.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Map;
import net.df.module.admin.model.User;
import net.df.module.admin.service.UserService;


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
        Long departmentId = MapUtils.getLongFromMap(map, "departmentId", null);
        List<User> list = userService.list(id, userName, nickName, mobileNo, userState, userPass, salt, departmentId, null, null);
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
        Long departmentId = MapUtils.getLongFromMap(map, "departmentId", null);
        User user = userService.add(userName, nickName, mobileNo, userState, userPass, salt, departmentId);
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
        Long departmentId = MapUtils.getLongFromMap(map, "departmentId", null);
        User user = userService.update(id, userName, nickName, mobileNo, userState, userPass, salt, departmentId);
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
        int num = userService.delete(id);
        return ResultUtils.success(null);
    }
}
