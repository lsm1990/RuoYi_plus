package com.ruoyi.spider.resolver;

import com.ruoyi.spider.domain.SpiderConfig;
import us.codecraft.webmagic.Page;

/**
 * 页面解析器
 */
public interface Resolver {
    void process(Page page, SpiderConfig spiderConfig);
}
