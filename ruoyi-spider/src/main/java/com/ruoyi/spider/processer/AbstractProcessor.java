package com.ruoyi.spider.processer;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.spider.MyConfigurableSpider;
import com.ruoyi.spider.domain.Cookie;
import com.ruoyi.spider.domain.SpiderConfig;
import com.ruoyi.spider.fast.FastConfigContext;
import com.ruoyi.spider.resolver.DefaultResolver;
import com.ruoyi.spider.resolver.Resolver;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 统一对页面进行解析处理
 */
@Slf4j
public abstract class AbstractProcessor implements PageProcessor {

    protected final Logger logger = LoggerFactory.getLogger(AbstractProcessor.class);

    protected SpiderConfig config;

    protected String uuid;
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    AbstractProcessor() {
    }

    AbstractProcessor(SpiderConfig config) {
        this(config, UUID.randomUUID().toString());
    }

    AbstractProcessor(SpiderConfig config, String uuid) {
        this.config = FastConfigContext.replaceConfigUid(config);
        this.uuid = uuid;
    }

    /**
     * 程序入口方法
     * @return 返回列表
     */
    public abstract CopyOnWriteArrayList<LinkedHashMap<String, String>> execute();

    @Override
    public void process(Page page) {
        Resolver resolver = new DefaultResolver();
        /*if (config.getAjaxRequest()) {
            resolver = new JsonResolver();
        }*/
        resolver.process(page, config);

    }

    @Override
    public Site getSite() {
        Site site = Site.me()
                .setCharset(config.getCharset())
                .setDomain(config.getDomain())
                .setUserAgent(config.getUa())
                .setSleepTime(Integer.valueOf(config.getSleepTime().toString()))
                .setRetryTimes(config.getRetryTimes());

        //添加抓包获取的cookie信息
        List<Cookie> cookies = config.getCookies();
        if (CollectionUtils.isNotEmpty(cookies)) {
            for (Cookie cookie : cookies) {
                if (StringUtils.isEmpty(cookie.getDomain())) {
                    site.addCookie(cookie.getName(), cookie.getValue());
                    continue;
                }
                site.addCookie(cookie.getDomain(), cookie.getName(), cookie.getValue());
            }
        }
        //添加请求头，有些网站会根据请求头判断该请求是由浏览器发起还是由爬虫发起的
        Map<String, String> headers = config.getHeaders();
        if (MapUtils.isNotEmpty(headers)) {
            Set<Map.Entry<String, String>> entrySet = headers.entrySet();
            for (Map.Entry<String, String> entry : entrySet) {
                site.addHeader(entry.getKey(), entry.getValue());
            }
        }
        return site;
    }

    /**
     * 校验参数
     *
     * @param t 待校验的参数
     */
    final <T> List<String> validateModel(T t) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);

        List<String> messageList = new ArrayList<>();
        for (ConstraintViolation<T> constraintViolation : constraintViolations) {
            messageList.add(constraintViolation.getMessage());
        }
        return messageList;
    }

    /**
     * 自定义管道的处理方法
     *
     * @param resultItems     收集数据
     * @param datas 数据集合
     */
    final void processData(ResultItems resultItems, List<LinkedHashMap<String, String>> datas, MyConfigurableSpider spider) {
        if (null == spider) {
            return;
        }
        Map<String, Object> map = resultItems.getAll();
        if (CollectionUtil.isEmpty(map)) {
            return;
        }
        LinkedHashMap<String, String> tempMap=new LinkedHashMap<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            tempMap.put(entry.getKey(),entry.getValue()==null?"":entry.getValue().toString());
        }
        datas.add(tempMap);
    }

    public SpiderConfig getSpiderConfig() {
        return config;
    }
}
