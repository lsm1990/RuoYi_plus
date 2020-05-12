package com.ruoyi.spider.test;

import com.ruoyi.spider.backend.FastSpiderBackendService;
import com.ruoyi.spider.domain.ExitWayEnum;
import com.ruoyi.spider.domain.SpiderConfig;
import com.ruoyi.spider.fast.FastConfigContext;
import com.ruoyi.spider.fast.FastConfigEnum;
import us.codecraft.webmagic.downloader.HttpClientDownloader;

public class JueJinTest {
    public static void main(String[] args) {
        String userId="5b90662de51d450e8b1370f6";

        SpiderConfig config = FastConfigContext.parseConfig(FastConfigEnum.JUEJIN);
        config.setUserId(userId)
        .setExitWay(ExitWayEnum.URL_COUNT)
        .setCount(3);

        FastSpiderBackendService service=new FastSpiderBackendService(config);
        service.start();

    }
}
