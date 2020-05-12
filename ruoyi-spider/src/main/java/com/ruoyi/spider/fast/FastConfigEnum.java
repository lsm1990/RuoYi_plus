package com.ruoyi.spider.fast;

/**
 * json配置文件配置的任务代码
 */
public enum FastConfigEnum {

    CNBLOGS("cnblogs", "博客园"),
    IMOOC("imooc", "慕课网"),
    CSDN("csdn", "CSDN"),
    OSCHINA("oschina", "oschina"),
    JUEJIN("juejin", "juejin"),
    V2EX("v2ex", "v2ex");



    private String code;
    private String name;

    FastConfigEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
