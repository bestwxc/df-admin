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
import com.df4j.module.admin.model.SystemMenu;
import com.df4j.module.admin.service.SystemMenuService;


@RestController
@RequestMapping("/api/admin")
public class SystemMenuController {

    private Logger logger = LoggerFactory.getLogger(SystemMenuController.class);

    @Autowired
    private SystemMenuService systemMenuService;

    /**
     * 查询
     * @param map
     * @return
     */
    @RequestMapping("/menu/list")
    public Result<List<SystemMenu>> list(@RequestBody Map<String,?> map){

        // 分页页码
        Integer pageNum = MapUtils.getIntegerFromMap(map, "pageNum", null);
        // 分页大小
        Integer pageSize = MapUtils.getIntegerFromMap(map, "pageSize", null);
        // 排序
        String sort = MapUtils.getStringFromMap(map, "sort", null);

        //Long id = MapUtils.getLongFromMap(map, "id", null);
        Field<Long> id = FieldUtils.getLongField(map, "id", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //String menuCode = MapUtils.getStringFromMap(map, "menuCode", null);
        Field<String> menuCode = FieldUtils.getStringField(map, "menuCode", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //String menuName = MapUtils.getStringFromMap(map, "menuName", null);
        Field<String> menuName = FieldUtils.getStringField(map, "menuName", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //String menuIcon = MapUtils.getStringFromMap(map, "menuIcon", null);
        Field<String> menuIcon = FieldUtils.getStringField(map, "menuIcon", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //String parentMenu = MapUtils.getStringFromMap(map, "parentMenu", null);
        Field<String> parentMenu = FieldUtils.getStringField(map, "parentMenu", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //String jumpPath = MapUtils.getStringFromMap(map, "jumpPath", null);
        Field<String> jumpPath = FieldUtils.getStringField(map, "jumpPath", false, BoundType.INCLUDE, BoundType.INCLUDE);
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

        List<SystemMenu> list = systemMenuService.list(id, menuCode, menuName, menuIcon, parentMenu, jumpPath, orderNum, flag, createTime, updateTime);
        return ResultUtils.success(pageNum, pageSize, null, list);
    }

    /**
     * 新增
     * @param map
     * @return
     */
    @RequestMapping("/menu/add")
    public Result<SystemMenu> add(@RequestBody Map<String,?> map){
        String menuCode = MapUtils.getStringFromMap(map, "menuCode", null);
        String menuName = MapUtils.getStringFromMap(map, "menuName", null);
        String menuIcon = MapUtils.getStringFromMap(map, "menuIcon", null);
        String parentMenu = MapUtils.getStringFromMap(map, "parentMenu", null);
        String jumpPath = MapUtils.getStringFromMap(map, "jumpPath", null);
        Integer orderNum = MapUtils.getIntegerFromMap(map, "orderNum", null);
        Integer flag = MapUtils.getIntegerFromMap(map, "flag", null);
        SystemMenu systemMenu = systemMenuService.add(menuCode, menuName, menuIcon, parentMenu, jumpPath, orderNum, flag);
        return ResultUtils.success(systemMenu);
    }

    /**
     * 更新
     * @param map
     * @return
     */
    @RequestMapping("/menu/update")
    public Result<SystemMenu> update(@RequestBody Map<String,?> map) {
        Long id = MapUtils.getLongFromMapNotNull(map, "id");
        String menuCode = MapUtils.getStringFromMap(map, "menuCode", null);
        String menuName = MapUtils.getStringFromMap(map, "menuName", null);
        String menuIcon = MapUtils.getStringFromMap(map, "menuIcon", null);
        String parentMenu = MapUtils.getStringFromMap(map, "parentMenu", null);
        String jumpPath = MapUtils.getStringFromMap(map, "jumpPath", null);
        Integer orderNum = MapUtils.getIntegerFromMap(map, "orderNum", null);
        Integer flag = MapUtils.getIntegerFromMap(map, "flag", null);
        SystemMenu systemMenu = systemMenuService.update(id, menuCode, menuName, menuIcon, parentMenu, jumpPath, orderNum, flag);
        return ResultUtils.success(systemMenu);
    }


    /**
     * 删除
     * @param map
     * @return
     */
    @RequestMapping("/menu/delete")
    public Result delete(@RequestBody Map<String,?> map) {
        Long id = MapUtils.getLongFromMapNotNull(map, "id");
        int num = systemMenuService.logicDelete(id);
        return ResultUtils.success(null);
    }
}
