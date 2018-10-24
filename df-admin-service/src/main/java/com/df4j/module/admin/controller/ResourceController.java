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
import com.df4j.module.admin.model.Resource;
import com.df4j.module.admin.service.ResourceService;


@RestController
@RequestMapping("/api/admin")
public class ResourceController {

    private Logger logger = LoggerFactory.getLogger(ResourceController.class);

    @Autowired
    private ResourceService resourceService;

    /**
     * 查询
     * @param map
     * @return
     */
    @RequestMapping("/resource/list")
    public Result<List<Resource>> list(@RequestBody Map<String,?> map){

        // 分页页码
        Integer pageNum = MapUtils.getIntegerFromMap(map, "pageNum", null);
        // 分页大小
        Integer pageSize = MapUtils.getIntegerFromMap(map, "pageSize", null);
        // 排序
        String sort = MapUtils.getStringFromMap(map, "sort", null);

        //Long id = MapUtils.getLongFromMap(map, "id", null);
        Field<Long> id = FieldUtils.getLongField(map, "id", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //Long menuId = MapUtils.getLongFromMap(map, "menuId", null);
        Field<Long> menuId = FieldUtils.getLongField(map, "menuId", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //Integer resourceType = MapUtils.getIntegerFromMap(map, "resourceType", null);
        Field<Integer> resourceType = FieldUtils.getIntegerField(map, "resourceType", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //String resourceCode = MapUtils.getStringFromMap(map, "resourceCode", null);
        Field<String> resourceCode = FieldUtils.getStringField(map, "resourceCode", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //String resourceName = MapUtils.getStringFromMap(map, "resourceName", null);
        Field<String> resourceName = FieldUtils.getStringField(map, "resourceName", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //String resourcePath = MapUtils.getStringFromMap(map, "resourcePath", null);
        Field<String> resourcePath = FieldUtils.getStringField(map, "resourcePath", false, BoundType.INCLUDE, BoundType.INCLUDE);
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

        if(ValidateUtils.notNull(pageNum) && ValidateUtils.notNull(pageSize)){
            PageHelper.startPage(pageNum, pageSize);
        }
        if(ValidateUtils.isNotEmptyString(sort)){
            PageHelper.orderBy(sort);
        }

        List<Resource> list = resourceService.list(id, menuId, resourceType, resourceCode, resourceName, resourcePath, description, orderNum, flag, createTime, updateTime);
        return ResultUtils.success(pageNum, pageSize, null, list);
    }

    /**
     * 新增
     * @param map
     * @return
     */
    @RequestMapping("/resource/add")
    public Result<Resource> add(@RequestBody Map<String,?> map){
        Long menuId = MapUtils.getLongFromMap(map, "menuId", null);
        Integer resourceType = MapUtils.getIntegerFromMap(map, "resourceType", null);
        String resourceCode = MapUtils.getStringFromMap(map, "resourceCode", null);
        String resourceName = MapUtils.getStringFromMap(map, "resourceName", null);
        String resourcePath = MapUtils.getStringFromMap(map, "resourcePath", null);
        String description = MapUtils.getStringFromMap(map, "description", null);
        Integer orderNum = MapUtils.getIntegerFromMap(map, "orderNum", null);
        Integer flag = MapUtils.getIntegerFromMap(map, "flag", null);
        Resource resource = resourceService.add(menuId, resourceType, resourceCode, resourceName, resourcePath, description, orderNum, flag);
        return ResultUtils.success(resource);
    }

    /**
     * 更新
     * @param map
     * @return
     */
    @RequestMapping("/resource/update")
    public Result<Resource> update(@RequestBody Map<String,?> map) {
        Long id = MapUtils.getLongFromMapNotNull(map, "id");
        Long menuId = MapUtils.getLongFromMap(map, "menuId", null);
        Integer resourceType = MapUtils.getIntegerFromMap(map, "resourceType", null);
        String resourceCode = MapUtils.getStringFromMap(map, "resourceCode", null);
        String resourceName = MapUtils.getStringFromMap(map, "resourceName", null);
        String resourcePath = MapUtils.getStringFromMap(map, "resourcePath", null);
        String description = MapUtils.getStringFromMap(map, "description", null);
        Integer orderNum = MapUtils.getIntegerFromMap(map, "orderNum", null);
        Integer flag = MapUtils.getIntegerFromMap(map, "flag", null);
        Resource resource = resourceService.update(id, menuId, resourceType, resourceCode, resourceName, resourcePath, description, orderNum, flag);
        return ResultUtils.success(resource);
    }


    /**
     * 删除
     * @param map
     * @return
     */
    @RequestMapping("/resource/delete")
    public Result delete(@RequestBody Map<String,?> map) {
        Long id = MapUtils.getLongFromMapNotNull(map, "id");
        int num = resourceService.logicDelete(id);
        return ResultUtils.success(null);
    }
}
