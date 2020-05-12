package com.ruoyi.cms.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 广告位对象 cms_ad
 * 
 * @author wujiyue
 * @date 2019-11-16
 */
public class Ad extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 广告位ID */
    private Long adId;

    /** 广告位编码 */
    @Excel(name = "广告位编码")
    private String adCode;

    /** 广告位名称 */
    @Excel(name = "广告位名称")
    private String adName;

    /** 宽度 */
    @Excel(name = "宽度")
    private Integer width;

    /** 高度 */
    @Excel(name = "高度")
    private Integer height;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 创建人ID */
    private String userId;

    /** 部门ID */
    private String deptId;

    /** 描述 */
    private String description;

    public void setAdId(Long adId) 
    {
        this.adId = adId;
    }

    public Long getAdId() 
    {
        return adId;
    }
    public void setAdCode(String adCode) 
    {
        this.adCode = adCode;
    }

    public String getAdCode() 
    {
        return adCode;
    }
    public void setAdName(String adName) 
    {
        this.adName = adName;
    }

    public String getAdName() 
    {
        return adName;
    }
    public void setWidth(Integer width) 
    {
        this.width = width;
    }

    public Integer getWidth() 
    {
        return width;
    }
    public void setHeight(Integer height) 
    {
        this.height = height;
    }

    public Integer getHeight() 
    {
        return height;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setDeptId(String deptId) 
    {
        this.deptId = deptId;
    }

    public String getDeptId() 
    {
        return deptId;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("adId", getAdId())
            .append("adCode", getAdCode())
            .append("adName", getAdName())
            .append("width", getWidth())
            .append("height", getHeight())
            .append("status", getStatus())
            .append("userId", getUserId())
            .append("deptId", getDeptId())
            .append("description", getDescription())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .toString();
    }
}
