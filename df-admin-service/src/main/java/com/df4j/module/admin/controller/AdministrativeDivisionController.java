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
import com.df4j.module.admin.model.AdministrativeDivision;
import com.df4j.module.admin.service.AdministrativeDivisionService;


@RestController
@RequestMapping("/api/admin")
public class AdministrativeDivisionController {

    private Logger logger = LoggerFactory.getLogger(AdministrativeDivisionController.class);

    @Autowired
    private AdministrativeDivisionService administrativeDivisionService;

    /**
     * 查询
     * @param map
     * @return
     */
    @RequestMapping("/division/list")
    public Result<List<AdministrativeDivision>> list(@RequestBody Map<String,?> map){
        //Long id = MapUtils.getLongFromMap(map, "id", null);
        Field<Long> id = FieldUtils.getLongField(map, "id", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //String divisionCode = MapUtils.getStringFromMap(map, "divisionCode", null);
        Field<String> divisionCode = FieldUtils.getStringField(map, "divisionCode", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //String divisionName = MapUtils.getStringFromMap(map, "divisionName", null);
        Field<String> divisionName = FieldUtils.getStringField(map, "divisionName", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //Long parentId = MapUtils.getLongFromMap(map, "parentId", null);
        Field<Long> parentId = FieldUtils.getLongField(map, "parentId", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //String parentDivisionCode = MapUtils.getStringFromMap(map, "parentDivisionCode", null);
        Field<String> parentDivisionCode = FieldUtils.getStringField(map, "parentDivisionCode", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //Integer divisionLevel = MapUtils.getIntegerFromMap(map, "divisionLevel", null);
        Field<Integer> divisionLevel = FieldUtils.getIntegerField(map, "divisionLevel", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //Integer levelAdjust = MapUtils.getIntegerFromMap(map, "levelAdjust", null);
        Field<Integer> levelAdjust = FieldUtils.getIntegerField(map, "levelAdjust", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //String divisionType = MapUtils.getStringFromMap(map, "divisionType", null);
        Field<String> divisionType = FieldUtils.getStringField(map, "divisionType", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //Integer orderNum = MapUtils.getIntegerFromMap(map, "orderNum", null);
        Field<Integer> orderNum = FieldUtils.getIntegerField(map, "orderNum", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //Integer flag = MapUtils.getIntegerFromMap(map, "flag", null);
        Field<Integer> flag = FieldUtils.getIntegerField(map, "flag", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //Date createTime = MapUtils.getDateFromMap(map, "createTime", null);
        Field<Date> createTime = FieldUtils.getDateField(map, "createTime", DateUtils.DATE_TIME_PATTERN, false, BoundType.INCLUDE, BoundType.INCLUDE);
        //Date updateTime = MapUtils.getDateFromMap(map, "updateTime", null);
        Field<Date> updateTime = FieldUtils.getDateField(map, "updateTime", DateUtils.DATE_TIME_PATTERN, false, BoundType.INCLUDE, BoundType.INCLUDE);
        List<AdministrativeDivision> list = administrativeDivisionService.list(id, divisionCode, divisionName, parentId, parentDivisionCode, divisionLevel, levelAdjust, divisionType, orderNum, flag, createTime, updateTime);
        return ResultUtils.success(list);
    }




}
