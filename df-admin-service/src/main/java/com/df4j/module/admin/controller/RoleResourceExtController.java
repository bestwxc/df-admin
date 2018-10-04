package com.df4j.module.admin.controller;


import com.df4j.module.admin.service.RoleResourceService;
import com.df4j.base.server.Result;
import com.df4j.base.utils.MapUtils;
import com.df4j.base.utils.ResultUtils;
import com.df4j.base.utils.ValidateUtils;
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
