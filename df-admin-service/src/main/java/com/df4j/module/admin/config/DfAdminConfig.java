package com.df4j.module.admin.config;

import com.df4j.module.admin.properties.DfAdminProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@EnableConfigurationProperties({
        DfAdminProperties.class
})
public class DfAdminConfig {

    /**
     * 线程池
     * @return
     */
    @Bean
    ExecutorService executorService(){
        ExecutorService executorService = Executors.newScheduledThreadPool(8);
        return executorService;
    }

    /**
     * 解析GB2312的restTemplate
     * @return
     */
    @Bean(name = "gb2313RestTemplate")
    RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> converterList = restTemplate.getMessageConverters();

        //重新设置StringHttpMessageConverter字符集为UTF-8，解决中文乱码问题
        HttpMessageConverter<?> converterTarget = null;
        for (HttpMessageConverter<?> item : converterList) {
            if (StringHttpMessageConverter.class == item.getClass()) {
                converterTarget = item;
                break;
            }
        }
        if (null != converterTarget) {
            converterList.remove(converterTarget);
        }
        converterList.add(1, new StringHttpMessageConverter(Charset.forName("GB2312")));
        return restTemplate;
    }
}
