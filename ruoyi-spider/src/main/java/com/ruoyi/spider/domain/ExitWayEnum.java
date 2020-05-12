package com.ruoyi.spider.domain;

/**
 * 爬虫任务退出方式
 */
public enum ExitWayEnum {
    /**
     * 默认方式,直到将所有匹配到的url抓取完成才会退出
     */
    DEFAULT("默认", 0),
    /*
     * 持续时间
     */
    DURATION("持续时间(s)", 60),
    /**
     * 抓取的条数
     */
    URL_COUNT("链接条数", 10);

    private String desc;
    private int defaultCount;

    ExitWayEnum(String desc, int defaultCount) {
        this.desc = desc;
        this.defaultCount = defaultCount;
    }

    public String getDesc() {
        return desc;
    }

    public int getDefaultCount() {
        return defaultCount;
    }
}
