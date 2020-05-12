package com.ruoyi.cms.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 邮件对象 cms_email
 * 
 * @author wujiyue
 * @date 2019-11-04
 */
public class Email extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 邮件类型 */
    @Excel(name = "邮件类型")
    private Integer emailType;

    /** 用户ID */
    @Excel(name = "用户ID")
    private String userId;

    /** 发送者邮箱 */
    @Excel(name = "发送者邮箱")
    private String fromEmail;

    /** 发送者邮箱密码 */
    @Excel(name = "发送者邮箱密码")
    private String fromEmailPwd;

    /** 接收者邮箱 */
    @Excel(name = "接收者邮箱")
    private String toEmail;

    /** 主题 */
    @Excel(name = "主题")
    private String subject;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 是否发送 */
    @Excel(name = "是否发送")
    private Integer sendFlag;

    /** 发送时间 */
    @Excel(name = "发送时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date sendTime;

    /** 发送类型 0立即 1定时 */
    @Excel(name = "发送类型 0立即 1定时")
    private String sendType;

    /** 定时时间 */
    @Excel(name = "定时时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date planTime;

    /** 抄送用户 */
    @Excel(name = "抄送用户")
    private String copyTo;

    /** 附件 */
    @Excel(name = "附件")
    private String attachfiles;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setEmailType(Integer emailType) 
    {
        this.emailType = emailType;
    }

    public Integer getEmailType() 
    {
        return emailType;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setFromEmail(String fromEmail) 
    {
        this.fromEmail = fromEmail;
    }

    public String getFromEmail() 
    {
        return fromEmail;
    }
    public void setFromEmailPwd(String fromEmailPwd) 
    {
        this.fromEmailPwd = fromEmailPwd;
    }

    public String getFromEmailPwd() 
    {
        return fromEmailPwd;
    }
    public void setToEmail(String toEmail) 
    {
        this.toEmail = toEmail;
    }

    public String getToEmail() 
    {
        return toEmail;
    }
    public void setSubject(String subject) 
    {
        this.subject = subject;
    }

    public String getSubject() 
    {
        return subject;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setSendFlag(Integer sendFlag) 
    {
        this.sendFlag = sendFlag;
    }

    public Integer getSendFlag() 
    {
        return sendFlag;
    }
    public void setSendTime(Date sendTime) 
    {
        this.sendTime = sendTime;
    }

    public Date getSendTime() 
    {
        return sendTime;
    }
    public void setSendType(String sendType)
    {
        this.sendType = sendType;
    }

    public String getSendType()
    {
        return sendType;
    }
    public void setPlanTime(Date planTime)
    {
        this.planTime = planTime;
    }

    public Date getPlanTime()
    {
        return planTime;
    }
    public void setCopyTo(String copyTo) 
    {
        this.copyTo = copyTo;
    }

    public String getCopyTo() 
    {
        return copyTo;
    }
    public void setAttachfiles(String attachfiles) 
    {
        this.attachfiles = attachfiles;
    }

    public String getAttachfiles() 
    {
        return attachfiles;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("emailType", getEmailType())
            .append("userId", getUserId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("fromEmail", getFromEmail())
            .append("fromEmailPwd", getFromEmailPwd())
            .append("toEmail", getToEmail())
            .append("subject", getSubject())
            .append("content", getContent())
            .append("sendFlag", getSendFlag())
            .append("sendTime", getSendTime())
            .append("sendType", getSendType())
            .append("planTime", getPlanTime())
            .append("copyTo", getCopyTo())
            .append("attachfiles", getAttachfiles())
            .toString();
    }
}
