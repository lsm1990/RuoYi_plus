package com.ruoyi.spider.domain;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.assertj.core.util.Lists;

import java.util.List;

/**
 * 爬虫字段对象 spider_field
 * 
 * @author wujiyue
 * @date 2019-11-12
 */
public class SpiderField extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 字段ID */
    private Long fieldId;

    /** 爬虫配置ID */
    private Long configId;

    /** 字段 */
    @Excel(name = "字段")
    private String field;

    /** 字段名称 */
    @Excel(name = "字段名称")
    private String fieldName;

    /** 提取类型 */
    @Excel(name = "提取类型")
    private String extractType;

    /** 提取规则 */
    @Excel(name = "提取规则")
    private String extractBy;

    /** 常量值 */
    @Excel(name = "常量值")
    private String constantValue;

    /** 元素的索引 */
    @Excel(name = "元素的索引")
    private String extractIndex;

    /** 处理规则 */
    @Excel(name = "处理规则")
    private String processRuleId;

    /** 是否是根据元素取值 */
    @Excel(name = "是否是根据元素取值")
    private String extractAttrFlag;

    /** 根据哪个元素取值 */
    @Excel(name = "根据哪个元素取值")
    private String extractAttr;

    private List<SpiderFiledRule> fieldRules= Lists.newArrayList();//规则字段，存放该字段文本处理规则

    public List<SpiderFiledRule> getFieldRules() {
        return fieldRules;
    }

    public SpiderField setFieldRules(List<SpiderFiledRule> fieldRules) {
        if(CollectionUtils.isNotEmpty(fieldRules)){
            this.fieldRules .addAll(fieldRules);
        }
        return this;
    }
    public SpiderField addFieldRule(SpiderFiledRule rule) {
        if(rule!=null){
            this.fieldRules.add(rule);
        }
        return this;
    }
    public void setFieldId(Long fieldId)
    {
        this.fieldId = fieldId;
    }

    public Long getFieldId() 
    {
        return fieldId;
    }
    public void setConfigId(Long configId) 
    {
        this.configId = configId;
    }

    public Long getConfigId() 
    {
        return configId;
    }
    public void setField(String field) 
    {
        this.field = field;
    }

    public String getField() 
    {
        return field;
    }
    public void setFieldName(String fieldName) 
    {
        this.fieldName = fieldName;
    }

    public String getFieldName() 
    {
        return fieldName;
    }
    public void setExtractType(String extractType) 
    {
        this.extractType = extractType;
    }

    public String getExtractType() 
    {
        return extractType;
    }
    public void setExtractBy(String extractBy) 
    {
        this.extractBy = extractBy;
    }

    public String getExtractBy() 
    {
        return extractBy;
    }
    public void setConstantValue(String constantValue) 
    {
        this.constantValue = constantValue;
    }

    public String getConstantValue() 
    {
        return constantValue;
    }
    public void setExtractIndex(String extractIndex) 
    {
        this.extractIndex = extractIndex;
    }

    public String getExtractIndex() 
    {
        return extractIndex;
    }
    public void setProcessRuleId(String processRuleId) 
    {
        this.processRuleId = processRuleId;
    }

    public String getProcessRuleId() 
    {
        return processRuleId;
    }
    public void setExtractAttrFlag(String extractAttrFlag) 
    {
        this.extractAttrFlag = extractAttrFlag;
    }

    public String getExtractAttrFlag() 
    {
        return extractAttrFlag;
    }
    public void setExtractAttr(String extractAttr) 
    {
        this.extractAttr = extractAttr;
    }

    public String getExtractAttr() 
    {
        return extractAttr;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("fieldId", getFieldId())
            .append("configId", getConfigId())
            .append("field", getField())
            .append("fieldName", getFieldName())
            .append("extractType", getExtractType())
            .append("extractBy", getExtractBy())
            .append("constantValue", getConstantValue())
            .append("extractIndex", getExtractIndex())
            .append("processRuleId", getProcessRuleId())
            .append("extractAttrFlag", getExtractAttrFlag())
            .append("extractAttr", getExtractAttr())
            .toString();
    }
}
