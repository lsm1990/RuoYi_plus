package com.ruoyi.spider.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 字段值处理规则对象 spider_filed_rule
 * 
 * @author wujiyue
 * @date 2019-11-14
 */
public class SpiderFiledRule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 字段ID */
    @Excel(name = "字段ID")
    private String fieldId;

    /** 数据处理规则 */
    @Excel(name = "数据处理规则")
    private String processType;

    /** 替换正则 */
    @Excel(name = "替换正则")
    private String replacereg;

    /** 替换内容 */
    @Excel(name = "替换内容")
    private String replacement;

    /** 截取字符串目标 */
    @Excel(name = "截取字符串目标")
    private String substrTarget;

    /** 排序 */
    @Excel(name = "排序")
    private Integer sort;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setFieldId(String fieldId) 
    {
        this.fieldId = fieldId;
    }

    public String getFieldId() 
    {
        return fieldId;
    }
    public void setProcessType(String processType) 
    {
        this.processType = processType;
    }

    public String getProcessType() 
    {
        return processType;
    }
    public void setReplacereg(String replacereg) 
    {
        this.replacereg = replacereg;
    }

    public String getReplacereg() 
    {
        return replacereg;
    }
    public void setReplacement(String replacement) 
    {
        this.replacement = replacement;
    }

    public String getReplacement() 
    {
        return replacement;
    }
    public void setSubstrTarget(String substrTarget) 
    {
        this.substrTarget = substrTarget;
    }

    public String getSubstrTarget() 
    {
        return substrTarget;
    }
    public void setSort(Integer sort) 
    {
        this.sort = sort;
    }

    public Integer getSort() 
    {
        return sort;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("fieldId", getFieldId())
            .append("processType", getProcessType())
            .append("replacereg", getReplacereg())
            .append("replacement", getReplacement())
            .append("substrTarget", getSubstrTarget())
            .append("sort", getSort())
            .toString();
    }
}
