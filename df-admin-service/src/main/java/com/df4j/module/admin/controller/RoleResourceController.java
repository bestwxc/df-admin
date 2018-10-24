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
import com.df4j.module.admin.model.RoleResource;
import com.df4j.module.admin.service.RoleResourceService;


//@RestController
@RequestMapping("/api/admin")
public class RoleResourceController {

    private Logger logger = LoggerFactory.getLogger(RoleResourceController.class);

    @Autowired
    private RoleResourceService roleResourceService;

    /**
     * 查询
     * @param map
     * @return
     */
    @RequestMapping("/role/resource/list")
    public Result<List<RoleResource>> list(@RequestBody Map<String,?> map){

        // 分页页码
        Integer pageNum = MapUtils.getIntegerFromMap(map, "pageNum", null);
        // 分页大小
        Integer pageSize = MapUtils.getIntegerFromMap(map, "pageSize", null);
        // 排序
        String sort = MapUtils.getStringFromMap(map, "sort", null);

        //Long id = MapUtils.getLongFromMap(map, "id", null);
        Field<Long> id = FieldUtils.getLongField(map, "id", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //Long roleId = MapUtils.getLongFromMap(map, "roleId", null);
        Field<Long> roleId = FieldUtils.getLongField(map, "roleId", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //Long resourceId = MapUtils.getLongFromMap(map, "resourceId", null);
        Field<Long> resourceId = FieldUtils.getLongField(map, "resourceId", false, BoundType.INCLUDE, BoundType.INCLUDE);
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

        List<RoleResource> list = roleResourceService.list(id, roleId, resourceId, flag, createTime, updateTime);
        return ResultUtils.success(pageNum, pageSize, null, list);
    }

    /**
     * 新增
     * @param map
     * @return
     */
    @RequestMapping("/role/resource/add")
    public Result<RoleResource> add(@RequestBody Map<String,?> map){
        Long roleId = MapUtils.getLongFromMap(map, "roleId", null);
        Long resourceId = MapUtils.getLongFromMap(map, "resourceId", null);
        Integer flag = MapUtils.getIntegerFromMap(map, "flag", null);
        RoleResource roleResource = roleResourceService.add(roleId, resourceId, flag);
        return ResultUtils.success(roleResource);
    }

    /**
     * 更新
     * @param map
     * @return
     */
    @RequestMapping("/role/resource/update")
    public Result<RoleResource> update(@RequestBody Map<String,?> map) {
        Long id = MapUtils.getLongFromMapNotNull(map, "id");
        Long roleId = MapUtils.getLongFromMap(map, "roleId", null);
        Long resourceId = MapUtils.getLongFromMap(map, "resourceId", null);
        Integer flag = MapUtils.getIntegerFromMap(map, "flag", null);
        RoleResource roleResource = roleResourceService.update(id, roleId, resourceId, flag);
        return ResultUtils.success(roleResource);
    }


    /**
     * 删除
     * @param map
     * @return
     */
    @RequestMapping("/role/resource/delete")
    public Result delete(@RequestBody Map<String,?> map) {
        Long id = MapUtils.getLongFromMapNotNull(map, "id");
        int num = roleResourceService.logicDelete(id);
        return ResultUtils.success(null);
    }
}
