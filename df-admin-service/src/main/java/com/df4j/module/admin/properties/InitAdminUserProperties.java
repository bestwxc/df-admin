package com.df4j.module.admin.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("df.admin.init-task.init-admin-user")
public class InitAdminUserProperties {
    private boolean enable = true;
    private boolean resetPass = false;
    private String username = "admin";
    private String password = "123456";
    private String mobileNo = "13888888888";

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isResetPass() {
        return resetPass;
    }

    public void setResetPass(boolean resetPass) {
        this.resetPass = resetPass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
}
