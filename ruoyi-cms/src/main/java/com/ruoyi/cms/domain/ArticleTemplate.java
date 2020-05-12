package com.ruoyi.cms.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 文章模板对象 cms_article_template
 * 
 * @author markbro
 * @date 2019-12-31
 */
public class ArticleTemplate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 文章模板名称 */
    @Excel(name = "文章模板名称")
    private String name;

    /** 标签 */
    @Excel(name = "标签")
    private String tags;

    private String tagNames;

    /** 用户ID */
    private String userId;

    /** 用户名称 */
    private String userName;

    /** 内容 */
    private String content;

    /** 共享类型 */
    @Excel(name = "共享类型")
    private String shareType;

    /** 权重 */
    private Long weight;

    /** 最热 */
    private Integer hotFalg;

    /** 最新 */
    private Integer newFlag;

    /** 审核标志 */
    @Excel(name = "审核标志")
    private Integer audit;

    /** 审核时间 */
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date auditTime;

    /** 审核人 */
    private String auditBy;

    /** 审核人名称 */
    private String auditName;

    /** 原因 */
    private String auditReason;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setTags(String tags) 
    {
        this.tags = tags;
    }

    public String getTags() 
    {
        return tags;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getTagNames() {
        return tagNames;
    }

    public void setTagNames(String tagNames) {
        this.tagNames = tagNames;
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
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setShareType(String shareType) 
    {
        this.shareType = shareType;
    }

    public String getShareType() 
    {
        return shareType;
    }
    public void setWeight(Long weight) 
    {
        this.weight = weight;
    }

    public Long getWeight() 
    {
        return weight;
    }
    public void setHotFalg(Integer hotFalg) 
    {
        this.hotFalg = hotFalg;
    }

    public Integer getHotFalg() 
    {
        return hotFalg;
    }
    public void setNewFlag(Integer newFlag) 
    {
        this.newFlag = newFlag;
    }

    public Integer getNewFlag() 
    {
        return newFlag;
    }
    public void setAudit(Integer audit) 
    {
        this.audit = audit;
    }

    public Integer getAudit() 
    {
        return audit;
    }
    public void setAuditTime(Date auditTime) 
    {
        this.auditTime = auditTime;
    }

    public Date getAuditTime() 
    {
        return auditTime;
    }
    public void setAuditBy(String auditBy) 
    {
        this.auditBy = auditBy;
    }

    public String getAuditBy() 
    {
        return auditBy;
    }
    public void setAuditName(String auditName) 
    {
        this.auditName = auditName;
    }

    public String getAuditName() 
    {
        return auditName;
    }
    public void setAuditReason(String auditReason) 
    {
        this.auditReason = auditReason;
    }

    public String getAuditReason() 
    {
        return auditReason;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("tags", getTags())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("content", getContent())
            .append("shareType", getShareType())
            .append("weight", getWeight())
            .append("hotFalg", getHotFalg())
            .append("newFlag", getNewFlag())
            .append("createTime", getCreateTime())
            .append("audit", getAudit())
            .append("auditTime", getAuditTime())
            .append("auditBy", getAuditBy())
            .append("auditName", getAuditName())
            .append("auditReason", getAuditReason())
            .toString();
    }
}
