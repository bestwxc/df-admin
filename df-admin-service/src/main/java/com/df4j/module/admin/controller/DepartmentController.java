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
        Long id = MapUtils.getLongFromMap(map, "id", null);
        String departmentCode = MapUtils.getStringFromMap(map, "departmentCode", null);
        String departmentName = MapUtils.getStringFromMap(map, "departmentName", null);
        String parentDepartmentCode = MapUtils.getStringFromMap(map, "parentDepartmentCode", null);
        String description = MapUtils.getStringFromMap(map, "description", null);
        Integer orderNum = MapUtils.getIntegerFromMap(map, "orderNum", null);
        Integer flag = MapUtils.getIntegerFromMap(map, "flag", null);
        List<Department> list = departmentService.list(id, departmentCode, departmentName, parentDepartmentCode, description, orderNum, flag, null, null);
        return ResultUtils.success(list);
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
