package net.df.module.admin.task;

import net.df.base.exception.DfException;
import net.df.base.utils.SpringUtils;
import net.df.module.admin.model.AdministrativeDivision;
import net.df.module.admin.service.AdministrativeDivisionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoadDivisionTask implements Runnable{

    private Logger logger = LoggerFactory.getLogger(LoadDivisionTask.class);

    private int retryTime = 0;
    private String url;
    private Long parentId;
    private Integer divisionLevel;
    private String parentDivisionCode;

    public LoadDivisionTask(String url, Long parentId, Integer divisionLevel, String parentDivisionCode) {
        this.url = url;
        this.parentId = parentId;
        this.divisionLevel = divisionLevel;
        this.parentDivisionCode = parentDivisionCode;
    }

    public void setRetryTime(int retryTime) {
        this.retryTime = retryTime;
    }

    public int getRetryTime() {
        return retryTime;
    }

    @Override
    public void run() {
        try {
            logger.info("run,url:{},parentId:{},parentDivisionCode:{}", url, parentId, parentDivisionCode);
            ResponseEntity<String> rsp = null;
            try{
                RestTemplate restTemplate = SpringUtils.getBean("gb2313RestTemplate", RestTemplate.class);
                rsp = restTemplate.getForEntity(url, String.class);
            }catch (Exception e){
                if(retryTime < 5){
                    logger.error("请求出错,url:{},parentId:{},parentDivisionCode:{},rety:{}", url, parentId, parentDivisionCode,retryTime);
                    this.retryTime = this.retryTime + 1;
                    SpringUtils.getBean(ExecutorService.class).submit(this);
                }else{
                    logger.error("请求出错超过5次,url:{},parentId:{},parentDivisionCode:{},rety:{}", url, parentId, parentDivisionCode,retryTime);
                }
                throw e;
            }
            String table = null;
            if (!HttpStatus.OK.equals(rsp.getStatusCode())) {
                logger.warn("请求出错,url:{},parentId:{},parentDivisionCode:{},rety:{}", url, parentId, parentDivisionCode,retryTime);
            } else {
                String content = rsp.getBody().replaceAll("[\\t\\n\\r]", "").toLowerCase();
                logger.info("请求的数据为:{}", content);
                String reg = "<table class=.?(provincetable|citytable|countytable|towntable|villagetable).+?table>";
                Pattern pattern = Pattern.compile(reg);
                Matcher m = pattern.matcher(content);
                logger.info("data:{}",m);
                while(m.find()) {
                    if(table == null){
                        table = m.group();
                    }else{
                        throw new DfException("匹配多个记录");
                    }
                }
                if(table == null){
                    throw new DfException("未匹配到记录");
                }

                String regex2 = divisionLevel == 1 ? "<td><a href=.+?</a></td>" : "<tr class=.?(citytr|countytr|towntr|villagetr).+?</tr>";
                Pattern pattern2 = Pattern.compile(regex2);
                Matcher m2 = pattern2.matcher(table);
                int i = 0;
                while (m2.find()){
                    this.hendleItem(i, m2.group());
                    i++;
                }
            }
        }catch (Exception e){
            logger.error("未处理异常,url:{},parentId:{},parentDivisionCode:{}", url, parentId, parentDivisionCode, e);
        }
    }
    private void hendleItem(int i, String data){
        logger.info("开始处理城市数据url:{},parentId:{},parentDivisionCode:{},data:{}", url, parentId, parentDivisionCode,data);
        AdministrativeDivisionService administrativeDivisionService = SpringUtils.getBean(AdministrativeDivisionService.class);
        String divisionCode = null;
        String divisionName = null;
        String divisionType = null;
        String childUrl = null;
        if(divisionLevel == 1){
            Matcher m = Pattern.compile("(?<=href.?').{2,20}html").matcher(data);
            while(m.find()){
                childUrl = m.group();
                divisionCode = childUrl.substring(0,2) + "0000000000";
            }
            m = Pattern.compile("(?<=html'>).{2,30}(?=<br)").matcher(data);
            while(m.find()){
                divisionName = m.group();
            }
        }else if(divisionLevel == 5){
            Matcher m = Pattern.compile("(?<=td>).+?(?=</td>)").matcher(data);
            List<String> list = new ArrayList<>();
            while (m.find()){
                list.add(m.group().replaceAll("<td>",""));
            }
            logger.info("list:{}",list);
            if(list.size() != 3){
                logger.error("解析数据不合法,url:{},parentId:{},parentDivisionCode:{},data:{}", url, parentId, parentDivisionCode,data);
                return;
            }
            divisionCode = list.get(0);
            divisionType = list.get(1);
            divisionName = list.get(2);
        }else{
            Matcher m = Pattern.compile("(?<=href.?').{2,20}html").matcher(data);
            while(m.find()){
                childUrl = m.group();
            }
            m = Pattern.compile("(?<=html'>).{2,30}(?=</a>)").matcher(data);
            List<String> list = new ArrayList<>();
            while (m.find()){
                list.add(m.group());
            }
            if(list.size() != 2){
                m = Pattern.compile("(?<=td>).+?(?=</td>)").matcher(data);
                list.clear();
                while (m.find()){
                    list.add(m.group().replaceAll("<td>",""));
                }
            }
            if(list.size() == 2){
                divisionCode = list.get(0);
                divisionName = list.get(1);
            }else if(list.size() == 3){
                divisionCode = list.get(0);
                divisionType = list.get(1);
                divisionName = list.get(2);
            }else{
                logger.error("解析数据不合法,url:{},parentId:{},parentDivisionCode:{},data:{}", url, parentId, parentDivisionCode, data);
                return;
            }
        }
        logger.info("divisionCode:{},divisionName:{},divisionType:{},childUrl:{}", divisionCode,divisionName,divisionType,childUrl);
        AdministrativeDivision administrativeDivision = administrativeDivisionService.add(divisionCode, divisionName, parentId, parentDivisionCode, divisionLevel, 0, divisionType, i, 0);
        ExecutorService executorService = SpringUtils.getBean(ExecutorService.class);
        if(childUrl != null){
            String url2 = url.substring(0, url.lastIndexOf("/") + 1) + childUrl;
            executorService.submit(new LoadDivisionTask(url2, administrativeDivision.getId(),divisionLevel + 1, divisionCode));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
