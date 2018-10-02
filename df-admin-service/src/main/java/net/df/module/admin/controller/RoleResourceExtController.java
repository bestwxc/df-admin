package net.df.module.admin.controller;


import net.df.base.server.Result;
import net.df.base.utils.MapUtils;
import net.df.base.utils.ResultUtils;
import net.df.base.utils.ValidateUtils;
import net.df.module.admin.service.RoleResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class RoleResourceExtController extends RoleResourceController{

    private Logger logger = LoggerFactory.getLogger(RoleResourceExtController.class);

    @Autowired
    private RoleResourceService roleResourceService;

    @Transactional
    @RequestMapping("/role/resource/sync")
    public Result syncRoleResource(@RequestBody Map<String,?> map){
        Long roleId = MapUtils.getLongFromMapNotNull(map, "roleId");
        //新增加的resourceId列表
        List addResourceIds = MapUtils.getListFromMap(map,"addResourceIds",null);
        //移除的role-resource关系记录的ID
        List removeRoleResourceIds = MapUtils.getListFromMap(map, "removeRoleResourceIds", null);
        if(ValidateUtils.notEmpty(removeRoleResourceIds)){
            for(Object removeRoleResourceId: removeRoleResourceIds){
                Long id = Long.valueOf(String.valueOf(removeRoleResourceId));
                roleResourceService.logicDelete(id);
            }
        }
        if(ValidateUtils.notEmpty(addResourceIds)){
            for(Object addResourceId: addResourceIds){
                Long id = Long.valueOf(String.valueOf(addResourceId));
                roleResourceService.add(roleId, id,0);
            }
        }
        return ResultUtils.success(null);
    }
}
