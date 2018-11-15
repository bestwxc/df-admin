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
import com.df4j.module.admin.model.MenuResource;
import com.df4j.module.admin.service.MenuResourceService;


@RestController
@RequestMapping("/api/admin")
public class MenuResourceController {

    private Logger logger = LoggerFactory.getLogger(MenuResourceController.class);

    @Autowired
    private MenuResourceService menuResourceService;

    /**
     * 查询
     * @param map
     * @return
     */
    @RequestMapping("/menu/resource/list")
    public Result<List<MenuResource>> list(@RequestBody Map<String,?> map){

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
        //Long resourceId = MapUtils.getLongFromMap(map, "resourceId", null);
        Field<Long> resourceId = FieldUtils.getLongField(map, "resourceId", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //Integer menuResourceType = MapUtils.getIntegerFromMap(map, "menuResourceType", null);
        Field<Integer> menuResourceType = FieldUtils.getIntegerField(map, "menuResourceType", false, BoundType.INCLUDE, BoundType.INCLUDE);
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

        List<MenuResource> list = menuResourceService.list(id, menuId, resourceId, menuResourceType, orderNum, flag, createTime, updateTime);
        return ResultUtils.success(pageNum, pageSize, null, list);
    }

    /**
     * 新增
     * @param map
     * @return
     */
    @RequestMapping("/menu/resource/add")
    public Result<MenuResource> add(@RequestBody Map<String,?> map){
        Long menuId = MapUtils.getLongFromMap(map, "menuId", null);
        Long resourceId = MapUtils.getLongFromMap(map, "resourceId", null);
        Integer menuResourceType = MapUtils.getIntegerFromMap(map, "menuResourceType", null);
        Integer orderNum = MapUtils.getIntegerFromMap(map, "orderNum", null);
        Integer flag = MapUtils.getIntegerFromMap(map, "flag", null);
        MenuResource menuResource = menuResourceService.add(menuId, resourceId, menuResourceType, orderNum, flag);
        return ResultUtils.success(menuResource);
    }

    /**
     * 更新
     * @param map
     * @return
     */
    @RequestMapping("/menu/resource/update")
    public Result<MenuResource> update(@RequestBody Map<String,?> map) {
        Long id = MapUtils.getLongFromMapNotNull(map, "id");
        Long menuId = MapUtils.getLongFromMap(map, "menuId", null);
        Long resourceId = MapUtils.getLongFromMap(map, "resourceId", null);
        Integer menuResourceType = MapUtils.getIntegerFromMap(map, "menuResourceType", null);
        Integer orderNum = MapUtils.getIntegerFromMap(map, "orderNum", null);
        Integer flag = MapUtils.getIntegerFromMap(map, "flag", null);
        MenuResource menuResource = menuResourceService.update(id, menuId, resourceId, menuResourceType, orderNum, flag);
        return ResultUtils.success(menuResource);
    }


    /**
     * 删除
     * @param map
     * @return
     */
    @RequestMapping("/menu/resource/delete")
    public Result delete(@RequestBody Map<String,?> map) {
        Long id = MapUtils.getLongFromMapNotNull(map, "id");
        int num = menuResourceService.logicDelete(id);
        return ResultUtils.success(null);
    }
}
