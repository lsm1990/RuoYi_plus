package com.ruoyi.third.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 短信发送历史对象 third_sms_his
 * 
 * @author wujiyue
 * @date 2019-10-11
 */
public class SmsHis extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private String yhid;

    /** 用户名称 */
    @Excel(name = "用户名称")
    private String yhmc;

    /** 运营商 */
    @Excel(name = "运营商")
    private String carrieroperator;

    /** 手机号 */
    @Excel(name = "手机号")
    private String phone;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 返回码 */
    @Excel(name = "返回码")
    private String returncode;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setYhid(String yhid) 
    {
        this.yhid = yhid;
    }

    public String getYhid() 
    {
        return yhid;
    }
    public void setYhmc(String yhmc) 
    {
        this.yhmc = yhmc;
    }

    public String getYhmc() 
    {
        return yhmc;
    }
    public void setCarrieroperator(String carrieroperator) 
    {
        this.carrieroperator = carrieroperator;
    }

    public String getCarrieroperator() 
    {
        return carrieroperator;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setReturncode(String returncode) 
    {
        this.returncode = returncode;
    }

    public String getReturncode() 
    {
        return returncode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("yhid", getYhid())
            .append("yhmc", getYhmc())
            .append("carrieroperator", getCarrieroperator())
            .append("phone", getPhone())
            .append("content", getContent())
            .append("returncode", getReturncode())
            .append("createTime", getCreateTime())
            .toString();
    }
}
