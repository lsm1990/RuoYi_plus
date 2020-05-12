package com.ruoyi.spider.domain;

public class SpiderException extends RuntimeException {

    public SpiderException(String message) {
        super("[spider]" + message);
    }

    public SpiderException(String message, Throwable cause) {
        super("[spider]" + message, cause);
    }
}