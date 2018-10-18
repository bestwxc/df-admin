package com.df4j.module.admin.sesurity.shiro;

import com.df4j.base.exception.DfException;
import com.df4j.base.utils.ValidateUtils;
import com.df4j.module.admin.model.*;
import com.df4j.module.admin.service.*;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 管理系统的realm
 */

public class DfAdminRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleResourceService roleResourceService;

    @Autowired
    private ResourceService resourceService;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo= new SimpleAuthorizationInfo();
        User user = userService.listOne(primaryPrincipal);
        Long userId = user.getId();
        List<UserRole> list = userRoleService.list(null, userId, null, 0, null, null);
        if(ValidateUtils.notEmpty(list)){
            for(UserRole userRole : list){
                Long roleId = userRole.getRoleId();
                Role role = roleService.listOne(roleId);
                if(ValidateUtils.notNull(role)){
                    authorizationInfo.addRole(role.getRoleCode());
                    List<RoleResource> roleResources = roleResourceService.list(null, role.getId(), null, 0, null, null);
                    if(ValidateUtils.notEmpty(roleResources)){
                        for(RoleResource roleResource: roleResources){
                            Resource resource = resourceService.listOne(roleResource.getResourceId());
                            if(ValidateUtils.notNull(resource)){
                                String stringPermission = resource.getResourceType() + "-" + resource.getResourceCode();
                                authorizationInfo.addStringPermission(stringPermission);
                            }
                        }
                    }
                }
            }
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String userName = token.getUsername();
        String password = new String(token.getPassword());
        User user = userService.listOne(userName);
        if(ValidateUtils.isNull(user)){
            throw new DfException("用户不存在");
        }
        String salt = user.getSalt();
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, user.getUserPass(), ByteSource.Util.bytes(salt), "dfAdminRealm");
        return authenticationInfo;
    }
}
