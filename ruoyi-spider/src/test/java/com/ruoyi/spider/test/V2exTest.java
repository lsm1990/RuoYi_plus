package com.ruoyi.spider.test;

import com.ruoyi.spider.backend.FastSpiderBackendService;
import com.ruoyi.spider.domain.ExitWayEnum;
import com.ruoyi.spider.domain.SpiderConfig;
import com.ruoyi.spider.fast.FastConfigContext;
import com.ruoyi.spider.fast.FastConfigEnum;

public class V2exTest {
    public static void main(String[] args) {
        String userId="AlibabaSS";
        SpiderConfig config = FastConfigContext.parseConfig(FastConfigEnum.V2EX);
        config.setUserId(userId)
        .setExitWay(ExitWayEnum.URL_COUNT)
        .setCount(3);

        FastSpiderBackendService service=new FastSpiderBackendService(config);
        service.start();

    }
}
