package com.df4j.module.admin.sesurity.shiro;

import com.df4j.module.admin.model.User;
import com.df4j.module.admin.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 管理系统的realm
 */

public class DfAdminRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String userName = token.getUsername();
        String password = new String(token.getPassword());
        User user = userService.listOne(null, userName, null, null, null, null, null, null, null, null, null);
        String salt = user.getSalt();
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, user.getUserPass(), ByteSource.Util.bytes(salt), "dfAdminRealm");
        return authenticationInfo;
    }
}
