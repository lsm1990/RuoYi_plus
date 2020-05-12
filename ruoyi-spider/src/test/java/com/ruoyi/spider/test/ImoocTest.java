package com.ruoyi.spider.test;

import com.ruoyi.spider.backend.FastSpiderBackendService;
import com.ruoyi.spider.domain.ExitWayEnum;
import com.ruoyi.spider.domain.SpiderConfig;
import com.ruoyi.spider.fast.FastConfigContext;
import com.ruoyi.spider.fast.FastConfigEnum;

public class ImoocTest {
    public static void main(String[] args) {
        String userId="1175248";
        SpiderConfig config = FastConfigContext.parseConfig(FastConfigEnum.IMOOC);
        config.setUserId(userId)
                .setExitWay(ExitWayEnum.URL_COUNT)
                .setCount(3);

        FastSpiderBackendService service=new FastSpiderBackendService(config);
        service.start();
    }
}
