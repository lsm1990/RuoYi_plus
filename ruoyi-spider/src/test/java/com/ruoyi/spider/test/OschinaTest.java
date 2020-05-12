package com.ruoyi.spider.test;

import com.ruoyi.spider.backend.FastSpiderBackendService;
import com.ruoyi.spider.domain.ExitWayEnum;
import com.ruoyi.spider.domain.SpiderConfig;
import com.ruoyi.spider.fast.FastConfigContext;
import com.ruoyi.spider.fast.FastConfigEnum;

public class OschinaTest {
    public static void main(String[] args) {
        String userId="haitaohu";
        SpiderConfig config = FastConfigContext.parseConfig(FastConfigEnum.OSCHINA);
        config.setUserId(userId)
        .setExitWay(ExitWayEnum.URL_COUNT)
        .setCount(3);

        FastSpiderBackendService service=new FastSpiderBackendService(config);
        service.start();

    }
}
