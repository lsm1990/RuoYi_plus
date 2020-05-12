package com.ruoyi.cms.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 励志短语对象 cms_short_words
 * 
 * @author wujiyue
 * @date 2019-11-22
 */
public class ShortWords extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 短语 */
    @Excel(name = "短语")
    private String shortWords;

    /** 标签 */
    @Excel(name = "标签")
    private String tagids;

    /** 用户ID */
    @Excel(name = "用户ID")
    private String userId;

    /** 字数 */
    @Excel(name = "字数")
    private Long wordsCount;

    /** 权重 */
    @Excel(name = "权重")
    private Long weight;

    /** 审核状态 */
    @Excel(name = "审核状态")
    private Integer auditState;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setShortWords(String shortWords) 
    {
        this.shortWords = shortWords;
    }

    public String getShortWords() 
    {
        return shortWords;
    }
    public void setTagids(String tagids) 
    {
        this.tagids = tagids;
    }

    public String getTagids() 
    {
        return tagids;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setWordsCount(Long wordsCount) 
    {
        this.wordsCount = wordsCount;
    }

    public Long getWordsCount() 
    {
        return wordsCount;
    }
    public void setWeight(Long weight) 
    {
        this.weight = weight;
    }

    public Long getWeight() 
    {
        return weight;
    }
    public void setAuditState(Integer auditState) 
    {
        this.auditState = auditState;
    }

    public Integer getAuditState() 
    {
        return auditState;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("shortWords", getShortWords())
            .append("tagids", getTagids())
            .append("userId", getUserId())
            .append("wordsCount", getWordsCount())
            .append("weight", getWeight())
            .append("auditState", getAuditState())
            .toString();
    }
}
