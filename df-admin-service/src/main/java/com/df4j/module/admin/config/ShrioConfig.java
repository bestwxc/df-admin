package com.df4j.module.admin.config;


import com.df4j.module.admin.sesurity.shiro.DfAdminRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShrioConfig {
    @Bean
    SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(dfAdminRealm());
        return securityManager;
    }

    @Bean
    ShiroFilterFactoryBean shiroFilter(){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        //拦截器.
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
        filterChainDefinitionMap.put("/**", "anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    DfAdminRealm dfAdminRealm(){
        DfAdminRealm dfAdminRealm = new DfAdminRealm();
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("SHA1");
        hashedCredentialsMatcher.setHashIterations(3);
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        dfAdminRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return dfAdminRealm;
    }
}
