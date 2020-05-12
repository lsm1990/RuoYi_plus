package com.ruoyi.spider.domain;

import com.ruoyi.spider.config.SpiderConstants;
import com.ruoyi.spider.fast.FastConfigContext;
import com.ruoyi.spider.util.FackUserAgentUtil;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.assertj.core.util.Lists;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.proxy.Proxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 爬虫配置对象 spider_config
 * 
 * @author wujiyue
 * @date 2019-11-11
 */
@Data
public class SpiderConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 爬虫配置ID */
    private Long id;
    /** 爬虫编码 */
    @Excel(name = "爬虫编码")
    private String spiderCode;
    /** 爬虫名称 */
    @Excel(name = "爬虫名称")
    private String spiderName;

    /** 入口地址 */
    private String entryUrls;

    /** 目标URL正则 */
    private String targetRegex;

    /** 存储的表名 */
    @Excel(name = "存储的表名")
    private String tableName;

    /** 网站根域名 */
    @Excel(name = "网站根域名")
    private String domain;

    /** 字符集 */
    @Excel(name = "字符集")
    private String charset = "utf8";

    /** 睡眠时间(ms) */
    @Excel(name = "睡眠时间(ms)")
    private Long sleepTime= 1000L;

    /** 重试次数 */
    @Excel(name = "重试次数")
    private Integer retryTimes = 2;

    /** 线程数量 */
    @Excel(name = "线程数量")
    private Long threadCount = 1L;

    /** 使用代理 */
    @Excel(name = "使用代理")
    private Integer useProxy =0 ;
    @Excel(name = "打印日志")
    private Integer showLog=1;
    /**
     * 退出方式{DURATION:爬虫持续的时间,URL_COUNT:抓取到的url数量}
     */
    private String exitWay = ExitWayEnum.URL_COUNT.toString();
    /**
     * 对应退出方式，当exitWay = URL_COUNT时，该值表示url数量，当exitWay = DURATION时，该值表示爬虫持续的时间
     */
    private int count;

    private Integer cascade=0;//默认0表示支持入口URL发现目标URL，而不从目标URL级联收集URL

    private List<Cookie> cookies = new ArrayList<>();
    private Map<String, String> headers = new HashMap<>();
    private String ua = FackUserAgentUtil.getUserAgent();
    /** 入口地址集合 */
    private List<String> entryUrlsList= Lists.newArrayList();

    private List<SpiderField> fieldsList=Lists.newArrayList();

    private List<Pipeline> pipelineList=Lists.newArrayList();
    /**
     * 是否转存图片，当选择true时会自动过滤原文中的img链接，调用端可选择将图片下载后替换掉原来的图片
     */
    private boolean convertImg = false;
    private List<Proxy> proxyList = new ArrayList<>();

    private String userId;//扩展字段，无数据库字段对应。对应于爬取某个人的博客的作者uid

    public static SpiderConfig create(){
        return new SpiderConfig();
    }
    public void setExitWay(String exitWay) {
        this.exitWay = exitWay;
    }

    public SpiderConfig setExitWay(ExitWayEnum exitWay) {
        this.exitWay = exitWay.toString();
        this.count = exitWay.getDefaultCount();
        return this;
    }
    public SpiderConfig setConvertImg(boolean convertImg) {
        this.convertImg = convertImg;
        return this;
    }

    public SpiderConfig setCount(int count) {
        this.count = count;
        return this;
    }

    public SpiderConfig setHeader(String key, String value) {
        Map<String, String> headers = this.getHeaders();
        headers.put(key, value);
        return this;
    }

    public SpiderConfig setHeader(String headersStr) {
        if (StringUtils.isNotEmpty(headersStr)) {
            String[] headerArr = headersStr.split("\r\n");
            for (String s : headerArr) {
                String[] header = s.split("=");
                setHeader(header[0], header[1]);
            }
        }
        return this;
    }

    public SpiderConfig setCookie(String domain, String key, String value) {
        List<Cookie> cookies = this.getCookies();
        cookies.add(new Cookie(domain, key, value));
        return this;
    }

    public SpiderConfig setCookie(String cookiesStr) {
        if (StringUtils.isNotEmpty(cookiesStr)) {
            List<Cookie> cookies = this.getCookies();
            String[] cookieArr = cookiesStr.split(";");
            for (String aCookieArr : cookieArr) {
                String[] cookieNode = aCookieArr.split("=");
                if (cookieNode.length <= 1) {
                    continue;
                }
                cookies.add(new Cookie(cookieNode[0].trim(), cookieNode[1].trim()));
            }
        }
        return this;
    }


    private void addProxy(Proxy proxy) {
        if (this.useProxy == 0 || null == this.useProxy || null == proxy) {
            return;
        }
        proxyList.add(proxy);
    }

    public SpiderConfig setProxy(String proxyStr) {
        if (this.useProxy  == 0 || null == this.useProxy || proxyStr == null) {
            return this;
        }
        String[] proxyArr = proxyStr.split(",");
        for (String s : proxyArr) {
            String[] proxy = s.split(":");
            if (proxy.length == 2) {
                this.addProxy(new Proxy(proxy[0], Integer.parseInt(proxy[1])));
            } else if (proxy.length == 4) {
                this.addProxy(new Proxy(proxy[0], Integer.parseInt(proxy[1]), proxy[2], proxy[3]));
            }
        }
        return this;
    }
    public SpiderConfig setEntryUrls(String entryUrls) {
        this.entryUrls = entryUrls;
        if(StringUtils.isNotEmpty(entryUrls)){
            String[] arr=entryUrls.split(",");
            for(String s:arr){
                if(StringUtils.isNotEmpty(s)){
                    entryUrlsList.add(s);
                }
            }
        }
        return this;
    }
    public SpiderConfig addEntryUrl(String entryUrl) {
        this.entryUrlsList.add(entryUrl);
        return this;
    }
    public void setEntryUrlsList(List<String> entryUrlsList) {
        this.entryUrlsList = entryUrlsList;
    }

    public SpiderConfig setFieldsList(List<SpiderField> fieldsList) {
        if(CollectionUtils.isEmpty(fieldsList)){
            return this;
        }
        this.fieldsList.addAll(fieldsList);
        return this;
    }

    /**
     * 增加一个爬取字段
     * @param field
     * @return
     */
    public SpiderConfig addField(SpiderField field) {
        if(field==null||StringUtils.isEmpty(field.getField())){
            return this;
        }
        this.fieldsList.add(field);
        return this;
    }
    /**
     * 增加一个数据管道
     * @param pipeline
     * @return
     */
    public SpiderConfig addPipeline(Pipeline pipeline) {
        if(pipeline==null){
            return this;
        }
        this.pipelineList.add(pipeline);
        return this;
    }

    /**
     * 增加一个xpath提取规则字段
     * @param field
     * @param fieldName
     * @param xpath
     * @return
     */
    public SpiderConfig addField(String field,String fieldName,String xpath) {
        SpiderField spiderField=new SpiderField();
        spiderField.setField(field);
        spiderField.setFieldName(fieldName);
        spiderField.setExtractType(SpiderConstants.FIELD_EXTRACT_TYPE_XPATH);
        spiderField.setExtractBy(xpath);
        this.fieldsList.add(spiderField);
        return this;
    }

    public SpiderConfig setTargetRegex(String targetRegex) {
        this.targetRegex = targetRegex;
        return this;
    }

    public SpiderConfig setThreadCount(Long threadCount) {
        this.threadCount = threadCount;
        return this;
    }

    public SpiderConfig setShowLog(Integer showLog) {
        this.showLog = showLog;
        return this;
    }

    public SpiderConfig setSleepTime(Long sleepTime) {
        this.sleepTime = sleepTime;
        return this;
    }

    public SpiderConfig setDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public SpiderConfig setCharset(String charset) {
        this.charset = charset;
        return this;
    }

    public SpiderConfig setRetryTimes(Integer retryTimes) {
        this.retryTimes = retryTimes;
        return this;
    }

    public SpiderConfig setUseProxy(Integer useProxy) {
        this.useProxy = useProxy;
        return this;
    }

    public SpiderConfig setCascade(Integer cascade) {
        this.cascade = cascade;
        return this;
    }

    /**
     * 爬取某个人博客时候博主的用户id
     * @param userId
     * @return
     */
    public SpiderConfig setUserId(String userId) {
        this.userId = userId;
        return FastConfigContext.replaceConfigUid(this);
    }
}
