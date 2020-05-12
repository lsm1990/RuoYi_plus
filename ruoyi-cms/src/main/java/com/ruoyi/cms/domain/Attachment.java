package com.ruoyi.cms.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 附件对象 cms_attachment
 * 
 * @author wujiyue
 * @date 2019-11-01
 */
public class Attachment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String attachId;

    /** 组ID */
    @Excel(name = "组ID")
    private String zid;

    /** 用户ID */
    @Excel(name = "用户ID")
    private String userId;

    /** 类型 */
    @Excel(name = "类型")
    private String fileType;

    /** 名称 */
    @Excel(name = "名称")
    private String fileName;

    /** 路径 */
    @Excel(name = "路径")
    private String filePath;

    /** URL */
    @Excel(name = "URL")
    private String fileUrl;

    /** 大小 */
    @Excel(name = "大小")
    private Long size;

    /** 排序 */
    @Excel(name = "排序")
    private Integer sort;

    private String suffix;

    //private String fileTypeName;

    public void setAttachId(String attachId) 
    {
        this.attachId = attachId;
    }

    public String getAttachId() 
    {
        return attachId;
    }
    public void setZid(String zid) 
    {
        this.zid = zid;
    }

    public String getZid() 
    {
        return zid;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setFileType(String fileType) 
    {
        this.fileType = fileType;
    }

    public String getFileType() 
    {
        return fileType;
    }
    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String getFileName() 
    {
        return fileName;
    }
    public void setFilePath(String filePath) 
    {
        this.filePath = filePath;
    }

    public String getFilePath() 
    {
        return filePath;
    }
    public void setSize(Long size) 
    {
        this.size = size;
    }

    public Long getSize() 
    {
        return size;
    }
    public void setSort(Integer sort) 
    {
        this.sort = sort;
    }

    public Integer getSort() 
    {
        return sort;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("attachId", getAttachId())
            .append("zid", getZid())
            .append("userId", getUserId())
            .append("fileType", getFileType())
            .append("fileName", getFileName())
            .append("filePath", getFilePath())
            .append("size", getSize())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("sort", getSort())
            .toString();
    }
}
