package net.df.module.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import net.df.base.server.Result;
import net.df.base.utils.MapUtils;
import net.df.base.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Map;
import net.df.module.admin.model.Department;
import net.df.module.admin.service.DepartmentService;


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
        Long parentDepartmentId = MapUtils.getLongFromMap(map, "parentDepartmentId", null);
        List<Department> list = departmentService.list(id, departmentCode, departmentName, parentDepartmentId, null, null);
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
        Long parentDepartmentId = MapUtils.getLongFromMap(map, "parentDepartmentId", null);
        Department department = departmentService.add(departmentCode, departmentName, parentDepartmentId);
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
        Long parentDepartmentId = MapUtils.getLongFromMap(map, "parentDepartmentId", null);
        Department department = departmentService.update(id, departmentCode, departmentName, parentDepartmentId);
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
        int num = departmentService.delete(id);
        return ResultUtils.success(null);
    }
}
