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
import com.df4j.module.admin.model.Department;
import com.df4j.module.admin.service.DepartmentService;


@RestController
@RequestMapping("/api/admin")
public class DepartmentController {

    private Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService departmentService;

    /**
     * 查询
     * @param map
     * @return
     */
    @RequestMapping("/department/list")
    public Result<List<Department>> list(@RequestBody Map<String,?> map){

        // 分页页码
        Integer pageNum = MapUtils.getIntegerFromMap(map, "pageNum", null);
        // 分页大小
        Integer pageSize = MapUtils.getIntegerFromMap(map, "pageSize", null);
        // 排序
        String sort = MapUtils.getStringFromMap(map, "sort", null);

        //Long id = MapUtils.getLongFromMap(map, "id", null);
        Field<Long> id = FieldUtils.getLongField(map, "id", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //String departmentCode = MapUtils.getStringFromMap(map, "departmentCode", null);
        Field<String> departmentCode = FieldUtils.getStringField(map, "departmentCode", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //String departmentName = MapUtils.getStringFromMap(map, "departmentName", null);
        Field<String> departmentName = FieldUtils.getStringField(map, "departmentName", false, BoundType.INCLUDE, BoundType.INCLUDE);
        //String parentDepartmentCode = MapUtils.getStringFromMap(map, "parentDepartmentCode", null);
        Field<String> parentDepartmentCode = FieldUtils.getStringField(map, "parentDepartmentCode", false, BoundType.INCLUDE, BoundType.INCLUDE);
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

        List<Department> list = departmentService.list(id, departmentCode, departmentName, parentDepartmentCode, description, orderNum, flag, createTime, updateTime);
        return ResultUtils.success(pageNum, pageSize, null, list);
    }

    /**
     * 新增
     * @param map
     * @return
     */
    @RequestMapping("/department/add")
    public Result<Department> add(@RequestBody Map<String,?> map){
        String departmentCode = MapUtils.getStringFromMap(map, "departmentCode", null);
        String departmentName = MapUtils.getStringFromMap(map, "departmentName", null);
        String parentDepartmentCode = MapUtils.getStringFromMap(map, "parentDepartmentCode", null);
        String description = MapUtils.getStringFromMap(map, "description", null);
        Integer orderNum = MapUtils.getIntegerFromMap(map, "orderNum", null);
        Integer flag = MapUtils.getIntegerFromMap(map, "flag", null);
        Department department = departmentService.add(departmentCode, departmentName, parentDepartmentCode, description, orderNum, flag);
        return ResultUtils.success(department);
    }

    /**
     * 更新
     * @param map
     * @return
     */
    @RequestMapping("/department/update")
    public Result<Department> update(@RequestBody Map<String,?> map) {
        Long id = MapUtils.getLongFromMapNotNull(map, "id");
        String departmentCode = MapUtils.getStringFromMap(map, "departmentCode", null);
        String departmentName = MapUtils.getStringFromMap(map, "departmentName", null);
        String parentDepartmentCode = MapUtils.getStringFromMap(map, "parentDepartmentCode", null);
        String description = MapUtils.getStringFromMap(map, "description", null);
        Integer orderNum = MapUtils.getIntegerFromMap(map, "orderNum", null);
        Integer flag = MapUtils.getIntegerFromMap(map, "flag", null);
        Department department = departmentService.update(id, departmentCode, departmentName, parentDepartmentCode, description, orderNum, flag);
        return ResultUtils.success(department);
    }


    /**
     * 删除
     * @param map
     * @return
     */
    @RequestMapping("/department/delete")
    public Result delete(@RequestBody Map<String,?> map) {
        Long id = MapUtils.getLongFromMapNotNull(map, "id");
        int num = departmentService.logicDelete(id);
        return ResultUtils.success(null);
    }
}
