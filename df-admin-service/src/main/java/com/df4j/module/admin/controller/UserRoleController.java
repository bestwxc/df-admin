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
import com.df4j.module.admin.model.UserRole;
import com.df4j.module.admin.service.UserRoleService;


//@RestController
@RequestMapping("/api/admin")
public class UserRoleController {

    private Logger logger = LoggerFactory.getLogger(UserRoleController.class);

    @Autowired
    private UserRoleService userRoleService;

    /**
     * 查询
     * @param map
     * @return
     */
    @RequestMapping("/user/role/list")
    public Result<List<UserRole>> list(@RequestBody Map<String,?> map){
        Long id = MapUtils.getLongFromMap(map, "id", null);
        Long userId = MapUtils.getLongFromMap(map, "userId", null);
        Long roleId = MapUtils.getLongFromMap(map, "roleId", null);
        Integer flag = MapUtils.getIntegerFromMap(map, "flag", null);
        List<UserRole> list = userRoleService.list(id, userId, roleId, flag, null, null);
        return ResultUtils.success(list);
    }

    /**
     * 新增
     * @param map
     * @return
     */
    @RequestMapping("/user/role/add")
    public Result<UserRole> add(@RequestBody Map<String,?> map){
        Long userId = MapUtils.getLongFromMap(map, "userId", null);
        Long roleId = MapUtils.getLongFromMap(map, "roleId", null);
        Integer flag = MapUtils.getIntegerFromMap(map, "flag", null);
        UserRole userRole = userRoleService.add(userId, roleId, flag);
        return ResultUtils.success(userRole);
    }

    /**
     * 更新
     * @param map
     * @return
     */
    @RequestMapping("/user/role/update")
    public Result<UserRole> update(@RequestBody Map<String,?> map) {
        Long id = MapUtils.getLongFromMapNotNull(map, "id");
        Long userId = MapUtils.getLongFromMap(map, "userId", null);
        Long roleId = MapUtils.getLongFromMap(map, "roleId", null);
        Integer flag = MapUtils.getIntegerFromMap(map, "flag", null);
        UserRole userRole = userRoleService.update(id, userId, roleId, flag);
        return ResultUtils.success(userRole);
    }


    /**
     * 删除
     * @param map
     * @return
     */
    @RequestMapping("/user/role/delete")
    public Result delete(@RequestBody Map<String,?> map) {
        Long id = MapUtils.getLongFromMapNotNull(map, "id");
        int num = userRoleService.logicDelete(id);
        return ResultUtils.success(null);
    }
}
