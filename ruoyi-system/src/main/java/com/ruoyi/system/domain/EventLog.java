package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 事件日志对象 sys_event_log
 * 
 * @author ruoyi
 * @date 2019-12-30
 */
public class EventLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private String userId;

    /** 用户名称 */
    @Excel(name = "用户名称")
    private String userName;

    /** 时间编号 */
    @Excel(name = "时间编号")
    private String eventCode;

    /** 时间名称 */
    @Excel(name = "时间名称")
    private String eventName;

    /** 来源 */
    @Excel(name = "来源")
    private String source;

    /** 参数 */
    @Excel(name = "参数")
    private String datas;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setEventCode(String eventCode) 
    {
        this.eventCode = eventCode;
    }

    public String getEventCode() 
    {
        return eventCode;
    }
    public void setEventName(String eventName) 
    {
        this.eventName = eventName;
    }

    public String getEventName() 
    {
        return eventName;
    }
    public void setSource(String source) 
    {
        this.source = source;
    }

    public String getSource() 
    {
        return source;
    }
    public void setDatas(String datas) 
    {
        this.datas = datas;
    }

    public String getDatas() 
    {
        return datas;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("eventCode", getEventCode())
            .append("eventName", getEventName())
            .append("source", getSource())
            .append("datas", getDatas())
            .append("createTime", getCreateTime())
            .toString();
    }
}
