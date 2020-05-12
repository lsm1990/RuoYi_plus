package com.ruoyi.ehcache.runner;

import com.ruoyi.ehcache.util.EhCacheUtils;
import net.sf.ehcache.Ehcache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * springboot 允许实现CommandLineRunner接口的类程序启动后run方法中做一些事情，比如加载缓存.
 */
@Component
@Order(value = 1)
public class InitEhCacheRunner implements CommandLineRunner {
    protected   final Logger log= LoggerFactory.getLogger(getClass());

    @Override
    public void run(String... args) throws Exception {
        log.info("InitEhCacheRunner加载Ehcache缓存信息Start>>>>>>>>>");
        //可以从数据库加载配置信息到缓存
        EhCacheUtils.putSysInfo("test_key","test_value");
        EhCacheUtils.putUserInfo("test_user_key","1","test_user_value");
        EhCacheUtils.putDefaultInfo("test_default_key","test_default_value");
        log.info("InitEhCacheRunner加载Ehcache缓存信息End<<<<<<<<");
    }
}
