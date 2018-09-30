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
import net.df.module.admin.model.Role;
import net.df.module.admin.service.RoleService;


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
        Long id = MapUtils.getLongFromMap(map, "id", null);
        String roleCode = MapUtils.getStringFromMap(map, "roleCode", null);
        String roleName = MapUtils.getStringFromMap(map, "roleName", null);
        Integer flag = MapUtils.getIntegerFromMap(map, "flag", null);
        List<Role> list = roleService.list(id, roleCode, roleName, flag, null, null);
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
        Integer flag = MapUtils.getIntegerFromMap(map, "flag", null);
        Role role = roleService.add(roleCode, roleName, flag);
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
        Integer flag = MapUtils.getIntegerFromMap(map, "flag", null);
        Role role = roleService.update(id, roleCode, roleName, flag);
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