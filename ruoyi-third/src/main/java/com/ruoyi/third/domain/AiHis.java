package com.ruoyi.third.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 人工智能图片识别历史对象 third_ai_his
 * 
 * @author wujiyue
 * @date 2019-10-12
 */
public class AiHis extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private String yhid;

    /** 用户名称 */
    @Excel(name = "用户名称")
    private String yhmc;

    /** 类型 */
    @Excel(name = "类型")
    private String aiType;

    /** 类型名称 */
    @Excel(name = "类型名称")
    private String typeName;

    /** 结果1成功0失败 */
    @Excel(name = "结果1成功0失败")
    private String result;

    /** 错误信息 */
    @Excel(name = "错误信息")
    private String errorMsg;

    /** 请求结果 */
    @Excel(name = "请求结果")
    private String jsonResult;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    public void setAiType(String aiType) 
    {
        this.aiType = aiType;
    }

    public String getAiType() 
    {
        return aiType;
    }
    public void setTypeName(String typeName) 
    {
        this.typeName = typeName;
    }

    public String getTypeName() 
    {
        return typeName;
    }
    public void setResult(String result) 
    {
        this.result = result;
    }

    public String getResult() 
    {
        return result;
    }
    public void setErrorMsg(String errorMsg) 
    {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() 
    {
        return errorMsg;
    }
    public void setJsonResult(String jsonResult) 
    {
        this.jsonResult = jsonResult;
    }

    public String getJsonResult() 
    {
        return jsonResult;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("yhid", getYhid())
            .append("yhmc", getYhmc())
            .append("aiType", getAiType())
            .append("typeName", getTypeName())
            .append("result", getResult())
            .append("errorMsg", getErrorMsg())
            .append("jsonResult", getJsonResult())
            .append("createTime", getCreateTime())
            .toString();
    }
}
