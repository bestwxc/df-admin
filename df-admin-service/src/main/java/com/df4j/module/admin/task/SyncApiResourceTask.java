package com.df4j.module.admin.task;

import com.df4j.base.utils.SpringUtils;
import com.df4j.base.utils.ValidateUtils;
import com.df4j.module.admin.model.Resource;
import com.df4j.module.admin.properties.SyncApiResourceProperties;
import com.df4j.module.admin.service.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import java.util.Map;
import java.util.Set;

public class SyncApiResourceTask implements Runnable{

    private Logger logger = LoggerFactory.getLogger(SyncApiResourceTask.class);

    @Override
    public void run() {
        SyncApiResourceProperties syncApiResourceProperties = SpringUtils.getBean(SyncApiResourceProperties.class);
        if(syncApiResourceProperties.isEnable()){
            logger.debug("检测到未开启同步api资源");
            return;
        }

        if(syncApiResourceProperties.isDeleleBefore()){
            // deleteBefore 还要删除关联的关系
        }

        RequestMappingHandlerMapping requestMappingHandlerMapping = SpringUtils.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
        for (RequestMappingInfo info : map.keySet()){
            //获取url的Set集合，一个方法可能对应多个url
            Set<String> patterns = info.getPatternsCondition().getPatterns();
            for (String url : patterns){
                logger.info("url:{}",url);
                syncApiResource(url);
            }
        }
    }

    private void syncApiResource(String url){
        if(ValidateUtils.isEmptyString(url)){
            return;
        }
        if(!url.startsWith("/api/")){
            return;
        }
        String resourceCode = url.substring(5);
        if(ValidateUtils.isEmptyString(resourceCode)){
            return;
        }
        ResourceService resourceService = SpringUtils.getBean(ResourceService.class);
        Resource resource = resourceService.listOne(resourceCode);
        if(ValidateUtils.isNull(resource)){
            resourceService.add(null,3, resourceCode, resourceCode,url, null, 0, 0);
        }else{
            if(!resourceCode.equals(resource.getResourceCode()) || !url.equals(resource.getResourcePath())){
                resourceService.update(resource.getId(), null, null, resourceCode, null, url, null, null, null);
            }
        }
    }
}
