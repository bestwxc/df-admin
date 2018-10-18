package com.df4j.module.admin.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("df.admin.init-task")
public class InitTaskProperties {

    private InitAdminUserProperties initAdminuser = new InitAdminUserProperties();

    private SyncApiResourceProperties syncApiResource = new SyncApiResourceProperties();

    public InitAdminUserProperties getInitAdminuser() {
        return initAdminuser;
    }

    public void setInitAdminuser(InitAdminUserProperties initAdminuser) {
        this.initAdminuser = initAdminuser;
    }

    public SyncApiResourceProperties getSyncApiResource() {
        return syncApiResource;
    }

    public void setSyncApiResource(SyncApiResourceProperties syncApiResource) {
        this.syncApiResource = syncApiResource;
    }
}
