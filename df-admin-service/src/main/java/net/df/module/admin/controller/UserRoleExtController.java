package net.df.module.admin.controller;

import net.df.base.server.Result;
import net.df.base.utils.MapUtils;
import net.df.base.utils.ResultUtils;
import net.df.base.utils.ValidateUtils;
import net.df.module.admin.service.UserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class UserRoleExtController extends UserRoleController{
    private Logger logger = LoggerFactory.getLogger(UserRoleExtController.class);

    @Autowired
    private UserRoleService userRoleService;

    @RequestMapping("/user/role/sync")
    public Result syncUserRole(@RequestBody Map<String,?> map){
        //用户ID
        Long userId = MapUtils.getLongFromMapNotNull(map, "userId");
        //新增的角色ID列表
        List addRoleIds = MapUtils.getListFromMap(map,"addRoleIds",null);
        //新增的用户-角色关系ID列表
        List removeUserRoleIds = MapUtils.getListFromMap(map, "removeUserRoleIds", null);
        if(ValidateUtils.notEmpty(removeUserRoleIds)){
            for(Object removeUserRoleId: removeUserRoleIds){
                Long id = Long.valueOf(String.valueOf(removeUserRoleId));
                userRoleService.logicDelete(id);
            }
        }
        if(ValidateUtils.notEmpty(addRoleIds)){
            for(Object addRoleId: addRoleIds){
                Long id = Long.valueOf(String.valueOf(addRoleId));
                userRoleService.add(userId, id,0);
            }
        }
        return ResultUtils.success(null);
    }
}
