package net.df;

import net.df.module.admin.controller.RoleResourceController;
import net.df.module.admin.task.LoadDivisionTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.concurrent.ExecutorService;

@MapperScan({
        "net.df.module.admin.mapper"
})
@SpringBootApplication
public class Application implements ApplicationRunner {

    private Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private ExecutorService executorService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }



    @Override
    public void run(ApplicationArguments args) throws Exception {

        // 爬取行政区域数据
        logger.info("run...");
        /*String url1 = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2017/index.html";
        String url2 = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2017/43.html";
        String url3 = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2017/43/4301.html";
        String url4 = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2017/43/01/430121.html";
        String url5 = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2017/43/01/21/430121004.html";*/
        //executorService.submit(new LoadDivisionTask(url1, 0l, 1,"86"));

        //处理异常数据
        /*
        String url6 = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2017/44/4419.html";
        executorService.submit(new LoadDivisionTask(url6, 720708l,2,"441900000000"));

        String url7 = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2017/44/4420.html";
        executorService.submit(new LoadDivisionTask(url7, 720709l,2,"442000000000"));

        String url8 = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2017/46/4604.html";
        executorService.submit(new LoadDivisionTask(url8, 720710l,2,"460400000000"));
        */

    }
}
