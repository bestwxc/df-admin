package com.df4j.module.admin.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("df.admin")
public class DfAdminProperties {

    private SecurityProperties security = new SecurityProperties();
    private InitTaskProperties initTask = new InitTaskProperties();

    public SecurityProperties getSecurity() {
        return security;
    }

    public void setSecurity(SecurityProperties security) {
        this.security = security;
    }
}
