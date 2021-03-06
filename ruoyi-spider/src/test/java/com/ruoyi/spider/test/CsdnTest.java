package com.ruoyi.spider.test;

import com.ruoyi.spider.backend.FastSpiderBackendService;
import com.ruoyi.spider.domain.ExitWayEnum;
import com.ruoyi.spider.domain.SpiderConfig;
import com.ruoyi.spider.fast.FastConfigContext;
import com.ruoyi.spider.fast.FastConfigEnum;

public class CsdnTest {
    public static void main(String[] args) {
        //注意要设置cookie,否则爬取不到
        String userId="u011197448";
        SpiderConfig config = FastConfigContext.parseConfig(FastConfigEnum.CSDN);
        config.setUserId(userId)
        .setExitWay(ExitWayEnum.URL_COUNT)
        .setCount(3);

        FastSpiderBackendService service=new FastSpiderBackendService(config);
        service.start();

    }
}
