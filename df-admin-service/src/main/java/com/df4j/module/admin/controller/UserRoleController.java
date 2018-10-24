package com.df4j.module.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.df4j.base.server.Result;
import com.df4j.base.utils.MapUtils;
import com.df4j.base.utils.FieldUtils;
import com.df4j.base.utils.DateUtils;
import com.df4j.base.utils.ValidateUtils;
import com.df4j.base.form.Field;
import com.df4j.base.form.BoundType;
import com.df4j.base.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
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

        // 分页页码
        Integer pageNum = MapUtils.getIntegerFromMap(map, "pageNum", null);
        // 分页大小
        Integer pageSize = MapUtils.getIntegerFromMap(map, "pageSize", null);
        // 排序
        String sort = MapUtils.getStringFromMap(map, "sort", null);

        //Long id = MapUtils.getLongFromMap(map, "id", null);
        Field<Long> id = FieldUtils.getLongField(map, "id", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //Long userId = MapUtils.getLongFromMap(map, "userId", null);
        Field<Long> userId = FieldUtils.getLongField(map, "userId", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //Long roleId = MapUtils.getLongFromMap(map, "roleId", null);
        Field<Long> roleId = FieldUtils.getLongField(map, "roleId", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //Integer flag = MapUtils.getIntegerFromMap(map, "flag", null);
        Field<Integer> flag = FieldUtils.getIntegerField(map, "flag", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //Date createTime = MapUtils.getDateFromMap(map, "createTime", null);
        Field<Date> createTime = FieldUtils.getDateField(map, "createTime", DateUtils.DATE_TIME_PATTERN, false, BoundType.INCLUDE, BoundType.INCLUDE);
        //Date updateTime = MapUtils.getDateFromMap(map, "updateTime", null);
        Field<Date> updateTime = FieldUtils.getDateField(map, "updateTime", DateUtils.DATE_TIME_PATTERN, false, BoundType.INCLUDE, BoundType.INCLUDE);

        if(ValidateUtils.notNull(pageNum) && ValidateUtils.notNull(pageSize)){
            PageHelper.startPage(pageNum, pageSize);
        }
        if(ValidateUtils.isNotEmptyString(sort)){
            PageHelper.orderBy(sort);
        }

        List<UserRole> list = userRoleService.list(id, userId, roleId, flag, createTime, updateTime);
        return ResultUtils.success(pageNum, pageSize, null, list);
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
