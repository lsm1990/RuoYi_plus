package com.ruoyi.cms.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 站内消息对象 cms_site_msg
 * 
 * @author wujiyue
 * @date 2019-11-17
 */
public class SiteMsg extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 发送者ID */
    @Excel(name = "发送者ID")
    private String fromId;

    /** 发送者名称 */
    @Excel(name = "发送者名称")
    private String fromName;

    /** 接受者ID */
    @Excel(name = "接受者ID")
    private String toId;

    /** 接受者名称 */
    @Excel(name = "接受者名称")
    private String toName;

    /** 消息类别 */
    @Excel(name = "消息类别")
    private String msgType;

    /** 消息内容 */
    @Excel(name = "消息内容")
    private String content;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setFromId(String fromId) 
    {
        this.fromId = fromId;
    }

    public String getFromId() 
    {
        return fromId;
    }
    public void setFromName(String fromName) 
    {
        this.fromName = fromName;
    }

    public String getFromName() 
    {
        return fromName;
    }
    public void setToId(String toId) 
    {
        this.toId = toId;
    }

    public String getToId() 
    {
        return toId;
    }
    public void setToName(String toName) 
    {
        this.toName = toName;
    }

    public String getToName() 
    {
        return toName;
    }
    public void setMsgType(String msgType) 
    {
        this.msgType = msgType;
    }

    public String getMsgType() 
    {
        return msgType;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("fromId", getFromId())
            .append("fromName", getFromName())
            .append("toId", getToId())
            .append("toName", getToName())
            .append("msgType", getMsgType())
            .append("content", getContent())
            .append("createTime", getCreateTime())
            .toString();
    }
}
