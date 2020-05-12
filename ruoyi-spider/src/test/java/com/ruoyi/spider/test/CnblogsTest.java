package com.ruoyi.spider.test;

import com.google.common.collect.Maps;
import com.ruoyi.common.core.domain.ICallBack;
import com.ruoyi.spider.backend.FastSpiderBackendService;
import com.ruoyi.spider.domain.ExitWayEnum;
import com.ruoyi.spider.domain.SpiderConfig;
import com.ruoyi.spider.fast.FastConfigContext;
import com.ruoyi.spider.fast.FastConfigEnum;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class CnblogsTest {
    public static void main(String[] args) {
        String userId="jinjiangongzuoshi";
        SpiderConfig config = FastConfigContext.parseConfig(FastConfigEnum.CNBLOGS);
        config.setUserId(userId)
        .setExitWay(ExitWayEnum.URL_COUNT)
        .setCount(3);

        TestCallBack callBack=new TestCallBack();//可以在回调函数中处理返回的数据集合
        FastSpiderBackendService service=new FastSpiderBackendService(config,callBack);
        service.start();

    }


    public static class TestCallBack implements ICallBack {
        Map params= Maps.newConcurrentMap();
        @Override
        public void onSuccess() {
            System.out.println(">>>>>>>>>>>>>CnblogsTest job done>>>>>>>>>>>>>>");
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
