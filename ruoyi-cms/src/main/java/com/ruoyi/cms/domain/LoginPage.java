package com.ruoyi.cms.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 登录页面对象 cms_login_page
 * 
 * @author wujiyue
 * @date 2019-12-24
 */
public class LoginPage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 登录主题名称 */
    @Excel(name = "登录主题名称")
    private String name;

    /** 页面代码 */
    @Excel(name = "页面代码")
    private String code;

    /** 封面图片 */
    @Excel(name = "封面图片")
    private String coverImg;

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
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setCoverImg(String coverImg) 
    {
        this.coverImg = coverImg;
    }

    public String getCoverImg() 
    {
        return coverImg;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("code", getCode())
            .append("coverImg", getCoverImg())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .toString();
    }
}
