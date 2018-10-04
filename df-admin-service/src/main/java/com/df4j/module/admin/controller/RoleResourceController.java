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
        Long id = MapUtils.getLongFromMap(map, "id", null);
        Long roleId = MapUtils.getLongFromMap(map, "roleId", null);
        Long resourceId = MapUtils.getLongFromMap(map, "resourceId", null);
        Integer flag = MapUtils.getIntegerFromMap(map, "flag", null);
        List<RoleResource> list = roleResourceService.list(id, roleId, resourceId, flag, null, null);
        return ResultUtils.success(list);
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
