package com.ruoyi.cms.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * PV对象 cms_pv
 * 
 * @author wujiyue
 * @date 2019-11-29
 */
public class Pv extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private String uid;

    /** 模块 */
    @Excel(name = "模块")
    private String module;

    /** 浏览器 */
    @Excel(name = "浏览器")
    private String browser;

    /** referer */
    @Excel(name = "referer")
    private String referer;

    /** 操作系统 */
    @Excel(name = "操作系统")
    private String os;

    /** 页面内容ID */
    private String pageId;

    /** URL */
    @Excel(name = "URL")
    private String url;

    /** 设备类型 */
    @Excel(name = "设备类型")
    private String deviceType;

    /** 时区 */
    @Excel(name = "时区")
    private String timeZone;

    /** ip地址 */
    @Excel(name = "ip地址")
    private String ip;

    /** 地址 */
    @Excel(name = "地址")
    private String location;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUid(String uid) 
    {
        this.uid = uid;
    }

    public String getUid() 
    {
        return uid;
    }
    public void setModule(String module) 
    {
        this.module = module;
    }

    public String getModule() 
    {
        return module;
    }
    public void setBrowser(String browser) 
    {
        this.browser = browser;
    }

    public String getBrowser() 
    {
        return browser;
    }
    public void setReferer(String referer) 
    {
        this.referer = referer;
    }

    public String getReferer() 
    {
        return referer;
    }
    public void setOs(String os) 
    {
        this.os = os;
    }

    public String getOs() 
    {
        return os;
    }
    public void setPageId(String pageId) 
    {
        this.pageId = pageId;
    }

    public String getPageId() 
    {
        return pageId;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }
    public void setDeviceType(String deviceType) 
    {
        this.deviceType = deviceType;
    }

    public String getDeviceType() 
    {
        return deviceType;
    }
    public void setTimeZone(String timeZone) 
    {
        this.timeZone = timeZone;
    }

    public String getTimeZone() 
    {
        return timeZone;
    }
    public void setIp(String ip) 
    {
        this.ip = ip;
    }

    public String getIp() 
    {
        return ip;
    }
    public void setLocation(String location) 
    {
        this.location = location;
    }

    public String getLocation() 
    {
        return location;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("uid", getUid())
            .append("module", getModule())
            .append("browser", getBrowser())
            .append("referer", getReferer())
            .append("os", getOs())
            .append("pageId", getPageId())
            .append("url", getUrl())
            .append("deviceType", getDeviceType())
            .append("timeZone", getTimeZone())
            .append("ip", getIp())
            .append("location", getLocation())
            .append("createTime", getCreateTime())
            .toString();
    }
}
