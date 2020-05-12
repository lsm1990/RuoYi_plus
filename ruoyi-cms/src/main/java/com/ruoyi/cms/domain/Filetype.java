package com.ruoyi.cms.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 文件类型对象 cms_filetype
 * 
 * @author wujiyue
 * @date 2019-11-01
 */
public class Filetype extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 后缀 */
    @Excel(name = "后缀")
    private String suffix;

    /** 中文名称 */
    @Excel(name = "中文名称")
    private String name;

    /** 图标 */
    @Excel(name = "图标")
    private String icon;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSuffix(String suffix) 
    {
        this.suffix = suffix;
    }

    public String getSuffix() 
    {
        return suffix;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setIcon(String icon) 
    {
        this.icon = icon;
    }

    public String getIcon() 
    {
        return icon;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("suffix", getSuffix())
            .append("name", getName())
            .append("icon", getIcon())
            .toString();
    }
}
