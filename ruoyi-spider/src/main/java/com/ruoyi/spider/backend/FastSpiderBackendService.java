package com.ruoyi.spider.backend;

import com.ruoyi.common.core.domain.ICallBack;
import com.ruoyi.spider.config.SpiderConstants;
import com.ruoyi.spider.domain.SpiderConfig;
import com.ruoyi.spider.processer.AbstractProcessor;
import com.ruoyi.spider.processer.DefalutProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 通过传入构建好的爬虫配置之后启动爬取任务
 */
public class FastSpiderBackendService extends Thread {
    protected final Logger logger = LoggerFactory.getLogger(FastSpiderBackendService.class);
    private ICallBack callBack;
    private SpiderConfig config;

    public FastSpiderBackendService(SpiderConfig config){
        this.config=config;
    }
    public FastSpiderBackendService(SpiderConfig config,ICallBack callBack){
        this.config=config;
        this.callBack=callBack;
    }
    @Override
    public void run() {
        AbstractProcessor processor=new DefalutProcessor(config);
        Date start=new Date();
        logger.info(">>>>>>>>>>>>爬虫任务开始>>>>>>>>>>>>");
        CopyOnWriteArrayList<LinkedHashMap<String, String>> datas = processor.execute();//执行爬虫
        Date end=new Date();
        Long timeSeconds=(end.getTime()-start.getTime())/1000;

        if(callBack!=null){
            Map<String,CopyOnWriteArrayList<LinkedHashMap<String, String>>> rmap=new HashMap();
            rmap.put("datas",datas);
            callBack.setParams(rmap);
            callBack.onSuccess();
        }
        logger.info(">>>>>>>>>>>>爬虫任务结束>>>>>耗时>"+timeSeconds+"秒>>>>>>>总计爬取到"+datas.size()+"条数据!");
    }
}
