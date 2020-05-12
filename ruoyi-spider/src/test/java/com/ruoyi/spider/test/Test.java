package com.ruoyi.spider.test;

import com.google.common.collect.Maps;
import com.ruoyi.common.core.domain.ICallBack;
import com.ruoyi.spider.backend.FastSpiderBackendService;
import com.ruoyi.spider.config.SpiderConstants;
import com.ruoyi.spider.domain.ExitWayEnum;
import com.ruoyi.spider.domain.SpiderConfig;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class Test {

    public static void main(String[] args) {
        SpiderConfig config=SpiderConfig.create()
        .setEntryUrls(SpiderConstants.URL_CNBLOGS)//设置入口地址
        .setTargetRegex(SpiderConstants.TARGET_URL_CNBLOGS)//设置目标url正则
        .addField("title","标题","//a[@id=cb_post_title_url]/html()")//配置数据提取规则
        .setExitWay(ExitWayEnum.URL_COUNT)
        .setCount(3)//爬取10条数据就结束
        .setShowLog(1)//关闭爬取内容日志展示
        /*.setSleepTime(500L) //睡眠时间2秒
        .setCascade(true) //开启子页面链接发现
        .setRetryTimes(2) //失败重试次数
        .setCharset("utf8") // 设置字符集
        .addPipeline(new Pipeline() { //  这里单纯的打印输出结果;可以自定义保存到数据库等。
            @Override
            public void process(ResultItems resultItems, Task task) {
              Map<String, Object> map =  resultItems.getAll();
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    System.out.println(entry.getKey() + " : " + entry.getValue());
                }
            }
        })
        .setThreadCount(2L)*/;//开启2个线程

        TestCallBack callBack=new TestCallBack();
        FastSpiderBackendService spider=new FastSpiderBackendService(config,callBack);
        spider.start();
    }

    public static class TestCallBack implements ICallBack {
        Map params= Maps.newConcurrentMap();
        @Override
        public void onSuccess() {
            System.out.println(">>>>>>>>>>>>>job done>>>>>>>>>>>>>>");
            CopyOnWriteArrayList<LinkedHashMap<String, String>> datas=(CopyOnWriteArrayList<LinkedHashMap<String, String>>)params.get("datas");
            System.out.println(">>>>>>>>>>>>>"+datas.size()+">>>>>>>>>>>>>>>");
        }

        @Override
        public void onFail() {

        }
        @Override
        public Map setParams(Map map) {
            params.clear();
            params.putAll(map);
            return params;
        }
    }
}
