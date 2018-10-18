package com.df4j.module.admin.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("df.admin.init-task.sync-api-resource")
public class SyncApiResourceProperties {

    private boolean enable = false;

    private boolean deleleBefore = false;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isDeleleBefore() {
        return deleleBefore;
    }

    public void setDeleleBefore(boolean deleleBefore) {
        this.deleleBefore = deleleBefore;
    }
}
