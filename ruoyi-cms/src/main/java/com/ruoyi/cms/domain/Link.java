package com.ruoyi.cms.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 链接对象 cms_link
 * 
 * @author wujiyue
 * @date 2019-11-26
 */
public class Link extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 链接ID */
    private Long linkId;

    /** 链接分类 */
    @Excel(name = "链接分类")
    private String linkType;

    /** 链接名称 */
    @Excel(name = "链接名称")
    private String linkName;

    /** 关键词 */
    @Excel(name = "关键词")
    private String keywords;

    /** 链接 */
    @Excel(name = "链接")
    private String link;

    /** 描述 */
    private String description;

    /** Logo */
    @Excel(name = "Logo")
    private String logo;

    /** 审核状态 */
    @Excel(name = "审核状态")
    private Integer auditState;

    /** 详情 */
    private String detail;

    /** 排序 */
    @Excel(name = "排序")
    private Long sort;

    /** 点赞数 */
    @Excel(name = "点赞数")
    private Long upVote;

    /** 允许评论 */
    @Excel(name = "允许评论")
    private Integer commentFlag;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    public void setLinkId(Long linkId) 
    {
        this.linkId = linkId;
    }

    public Long getLinkId() 
    {
        return linkId;
    }
    public void setLinkType(String linkType) 
    {
        this.linkType = linkType;
    }

    public String getLinkType() 
    {
        return linkType;
    }
    public void setLinkName(String linkName) 
    {
        this.linkName = linkName;
    }

    public String getLinkName() 
    {
        return linkName;
    }
    public void setKeywords(String keywords) 
    {
        this.keywords = keywords;
    }

    public String getKeywords() 
    {
        return keywords;
    }
    public void setLink(String link) 
    {
        this.link = link;
    }

    public String getLink() 
    {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLogo(String logo)
    {
        this.logo = logo;
    }

    public String getLogo() 
    {
        return logo;
    }
    public void setAuditState(Integer auditState) 
    {
        this.auditState = auditState;
    }

    public Integer getAuditState() 
    {
        return auditState;
    }
    public void setDetail(String detail) 
    {
        this.detail = detail;
    }

    public String getDetail() 
    {
        return detail;
    }
    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
    }
    public void setUpVote(Long upVote) 
    {
        this.upVote = upVote;
    }

    public Long getUpVote() 
    {
        return upVote;
    }
    public void setCommentFlag(Integer commentFlag) 
    {
        this.commentFlag = commentFlag;
    }

    public Integer getCommentFlag() 
    {
        return commentFlag;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("linkId", getLinkId())
            .append("linkType", getLinkType())
            .append("linkName", getLinkName())
            .append("keywords", getKeywords())
            .append("link", getLink())
            .append("description", getDescription())
            .append("logo", getLogo())
            .append("auditState", getAuditState())
            .append("detail", getDetail())
            .append("sort", getSort())
            .append("upVote", getUpVote())
            .append("commentFlag", getCommentFlag())
            .append("status", getStatus())
            .toString();
    }
}
