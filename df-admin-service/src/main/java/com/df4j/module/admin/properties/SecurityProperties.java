package com.df4j.module.admin.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties("df.admin.security")
public class SecurityProperties {
    public static List<String> defaultExcludes;
    static{
        defaultExcludes = new ArrayList<>();
        defaultExcludes.add("admin/user/login");
        defaultExcludes.add("admin/user/logout");
        defaultExcludes.add("admin/user/passwd");
        defaultExcludes.add("admin/image/code");
        defaultExcludes.add("admin/tree/list");
    }
    private List<String> excludes = defaultExcludes;

    public List<String> getExcludes() {
        return excludes;
    }

    public void setExcludes(List<String> excludes) {
        this.excludes = excludes;
    }
}
