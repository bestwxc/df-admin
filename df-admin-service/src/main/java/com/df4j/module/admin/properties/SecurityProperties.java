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

    private String algorithm = "SHA1";
    private int hashIterations = 3;
    private List<String> excludes = defaultExcludes;

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public int getHashIterations() {
        return hashIterations;
    }

    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }

    public List<String> getExcludes() {
        return excludes;
    }

    public void setExcludes(List<String> excludes) {
        if(excludes != null){
            this.excludes = excludes;
            this.excludes.addAll(defaultExcludes);
        } else {
            this.excludes = defaultExcludes;
        }
    }
}
