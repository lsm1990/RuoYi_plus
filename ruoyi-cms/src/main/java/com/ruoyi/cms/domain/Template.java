package com.ruoyi.cms.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 模板对象 cms_template
 * 
 * @author wujiyue
 * @date 2019-11-17
 */
public class Template extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long templateId;

    /** 模板代码 */
    @Excel(name = "模板代码")
    private String templateCode;

    /** 模板分类 */
    @Excel(name = "模板分类")
    private String templateType;

    /** 模板名称 */
    @Excel(name = "模板名称")
    private String templateName;

    /** 模板内容 */
    @Excel(name = "模板内容")
    private String templateContent;

    public void setTemplateId(Long templateId) 
    {
        this.templateId = templateId;
    }

    public Long getTemplateId() 
    {
        return templateId;
    }
    public void setTemplateCode(String templateCode) 
    {
        this.templateCode = templateCode;
    }

    public String getTemplateCode() 
    {
        return templateCode;
    }
    public void setTemplateType(String templateType) 
    {
        this.templateType = templateType;
    }

    public String getTemplateType() 
    {
        return templateType;
    }
    public void setTemplateName(String templateName) 
    {
        this.templateName = templateName;
    }

    public String getTemplateName() 
    {
        return templateName;
    }
    public void setTemplateContent(String templateContent) 
    {
        this.templateContent = templateContent;
    }

    public String getTemplateContent() 
    {
        return templateContent;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("templateId", getTemplateId())
            .append("templateCode", getTemplateCode())
            .append("templateType", getTemplateType())
            .append("templateName", getTemplateName())
            .append("templateContent", getTemplateContent())
            .toString();
    }
}
