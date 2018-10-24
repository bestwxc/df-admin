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
import com.df4j.module.admin.model.Role;
import com.df4j.module.admin.service.RoleService;


@RestController
@RequestMapping("/api/admin")
public class RoleController {

    private Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    /**
     * 查询
     * @param map
     * @return
     */
    @RequestMapping("/role/list")
    public Result<List<Role>> list(@RequestBody Map<String,?> map){
        //Long id = MapUtils.getLongFromMap(map, "id", null);
        Field<Long> id = FieldUtils.getLongField(map, "id", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //String roleCode = MapUtils.getStringFromMap(map, "roleCode", null);
        Field<String> roleCode = FieldUtils.getStringField(map, "roleCode", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //String roleName = MapUtils.getStringFromMap(map, "roleName", null);
        Field<String> roleName = FieldUtils.getStringField(map, "roleName", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //String description = MapUtils.getStringFromMap(map, "description", null);
        Field<String> description = FieldUtils.getStringField(map, "description", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //Integer orderNum = MapUtils.getIntegerFromMap(map, "orderNum", null);
        Field<Integer> orderNum = FieldUtils.getIntegerField(map, "orderNum", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //Integer flag = MapUtils.getIntegerFromMap(map, "flag", null);
        Field<Integer> flag = FieldUtils.getIntegerField(map, "flag", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //Date createTime = MapUtils.getDateFromMap(map, "createTime", null);
        Field<Date> createTime = FieldUtils.getDateField(map, "createTime", DateUtils.DATE_TIME_PATTERN, false, BoundType.INCLUDE, BoundType.INCLUDE);
        //Date updateTime = MapUtils.getDateFromMap(map, "updateTime", null);
        Field<Date> updateTime = FieldUtils.getDateField(map, "updateTime", DateUtils.DATE_TIME_PATTERN, false, BoundType.INCLUDE, BoundType.INCLUDE);
        List<Role> list = roleService.list(id, roleCode, roleName, description, orderNum, flag, createTime, updateTime);
        return ResultUtils.success(list);
    }

    /**
     * 新增
     * @param map
     * @return
     */
    @RequestMapping("/role/add")
    public Result<Role> add(@RequestBody Map<String,?> map){
        String roleCode = MapUtils.getStringFromMap(map, "roleCode", null);
        String roleName = MapUtils.getStringFromMap(map, "roleName", null);
        String description = MapUtils.getStringFromMap(map, "description", null);
        Integer orderNum = MapUtils.getIntegerFromMap(map, "orderNum", null);
        Integer flag = MapUtils.getIntegerFromMap(map, "flag", null);
        Role role = roleService.add(roleCode, roleName, description, orderNum, flag);
        return ResultUtils.success(role);
    }

    /**
     * 更新
     * @param map
     * @return
     */
    @RequestMapping("/role/update")
    public Result<Role> update(@RequestBody Map<String,?> map) {
        Long id = MapUtils.getLongFromMapNotNull(map, "id");
        String roleCode = MapUtils.getStringFromMap(map, "roleCode", null);
        String roleName = MapUtils.getStringFromMap(map, "roleName", null);
        String description = MapUtils.getStringFromMap(map, "description", null);
        Integer orderNum = MapUtils.getIntegerFromMap(map, "orderNum", null);
        Integer flag = MapUtils.getIntegerFromMap(map, "flag", null);
        Role role = roleService.update(id, roleCode, roleName, description, orderNum, flag);
        return ResultUtils.success(role);
    }


    /**
     * 删除
     * @param map
     * @return
     */
    @RequestMapping("/role/delete")
    public Result delete(@RequestBody Map<String,?> map) {
        Long id = MapUtils.getLongFromMapNotNull(map, "id");
        int num = roleService.logicDelete(id);
        return ResultUtils.success(null);
    }
}
