package com.ruoyi.cms.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.List;

/**
 * 链接分类对象 cms_link_type
 * 
 * @author wujiyue
 * @date 2019-11-26
 */
public class LinkType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 链接分类 */
    @Excel(name = "链接分类")
    private String linkType;

    /** 链接分类名称 */
    @Excel(name = "链接分类名称")
    private String linkTypeName;
    /** 链接分类描述 */
    @Excel(name = "链接分类描述")
    private String description;
    /** 封面图片 */
    @Excel(name = "封面图片")
    private String coverImage;

    /** 排序 */
    @Excel(name = "排序")
    private Integer sort;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    private List<Link> children;//扩展字段

    public void setId(Long id) 
    {
        this.id = id;
    }

    public List<Link> getChildren() {
        return children;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setChildren(List<Link> children) {
        this.children = children;
    }

    public Long getId()
    {
        return id;
    }
    public void setLinkType(String linkType) 
    {
        this.linkType = linkType;
    }

    public String getLinkType() 
    {
        return linkType;
    }
    public void setLinkTypeName(String linkTypeName) 
    {
        this.linkTypeName = linkTypeName;
    }

    public String getLinkTypeName() 
    {
        return linkTypeName;
    }
    public void setCoverImage(String coverImage) 
    {
        this.coverImage = coverImage;
    }

    public String getCoverImage() 
    {
        return coverImage;
    }
    public void setSort(Integer sort) 
    {
        this.sort = sort;
    }

    public Integer getSort() 
    {
        return sort;
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
            .append("id", getId())
            .append("linkType", getLinkType())
            .append("linkTypeName", getLinkTypeName())
            .append("coverImage", getCoverImage())
            .append("sort", getSort())
            .append("status", getStatus())
            .toString();
    }
}
