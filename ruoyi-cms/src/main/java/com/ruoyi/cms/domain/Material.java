package com.ruoyi.cms.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 素材对象 cms_material
 * 
 * @author wujiyue
 * @date 2019-11-05
 */
public class Material extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String materialId;

    /** 分组id */
    private String groupId;
    /** 分组名称 */
    private String groupName;//扩展

    private Long deptId;//部门id
    /** 素材名称 */
    @Excel(name = "素材名称")
    private String materialName;

    /** 素材类型 字典 1图片2视频3文本 */
    @Excel(name = "素材类型 字典 1图片2视频3文本")
    private String materialType;

    /** 素材描述 */
    @Excel(name = "素材描述")
    private String description;

    /** 素材大小 */
    @Excel(name = "素材大小")
    private String materialSize;

    /** 保存路径 */
    private String savePath;

    /** 缩略图 */
    private String thumbnail;

    /** 审核状态 0待审批2未通过1通过 */
    @Excel(name = "审核状态 0待审批2未通过1通过")
    private String auditState;

    /** 审核意见 */
    @Excel(name = "审核意见")
    private String auditReason;

    /** 可用状态 0停用1启用 */
    @Excel(name = "可用状态 0停用1启用")
    private String useState;

    /** 分辨率 宽 */
    @Excel(name = "分辨率 宽")
    private String width;

    /** 分辨率 高 */
    @Excel(name = "分辨率 高")
    private String height;

    /** 上传者 id */
    private String uploaderId;

    /** 上传日期 */
    @Excel(name = "上传日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uploadTime;
   // private String[] paramMaterialIds;//扩展字段
    public String albumId;//扩展字段

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

  /*  public String[] getParamMaterialIds() {
        return paramMaterialIds;
    }

    public void setParamMaterialIds(String[] paramMaterialIds) {
        this.paramMaterialIds = paramMaterialIds;
    }*/
    public void setMaterialId(String materialId) 
    {
        this.materialId = materialId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getMaterialId()
    {
        return materialId;
    }
    public void setGroupId(String groupId) 
    {
        this.groupId = groupId;
    }

    public String getGroupId() 
    {
        return groupId;
    }
    public void setMaterialName(String materialName) 
    {
        this.materialName = materialName;
    }

    public String getMaterialName() 
    {
        return materialName;
    }
    public void setMaterialType(String materialType) 
    {
        this.materialType = materialType;
    }

    public String getMaterialType() 
    {
        return materialType;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setMaterialSize(String materialSize) 
    {
        this.materialSize = materialSize;
    }

    public String getMaterialSize() 
    {
        return materialSize;
    }
    public void setSavePath(String savePath) 
    {
        this.savePath = savePath;
    }

    public String getSavePath() 
    {
        return savePath;
    }
    public void setThumbnail(String thumbnail) 
    {
        this.thumbnail = thumbnail;
    }

    public String getThumbnail() 
    {
        return thumbnail;
    }
    public void setAuditState(String auditState) 
    {
        this.auditState = auditState;
    }

    public String getAuditState() 
    {
        return auditState;
    }
    public void setAuditReason(String auditReason) 
    {
        this.auditReason = auditReason;
    }

    public String getAuditReason() 
    {
        return auditReason;
    }
    public void setUseState(String useState) 
    {
        this.useState = useState;
    }

    public String getUseState() 
    {
        return useState;
    }
    public void setWidth(String width) 
    {
        this.width = width;
    }

    public String getWidth() 
    {
        return width;
    }
    public void setHeight(String height) 
    {
        this.height = height;
    }

    public String getHeight() 
    {
        return height;
    }
    public void setUploaderId(String uploaderId) 
    {
        this.uploaderId = uploaderId;
    }

    public String getUploaderId() 
    {
        return uploaderId;
    }
    public void setUploadTime(Date uploadTime) 
    {
        this.uploadTime = uploadTime;
    }

    public Date getUploadTime() 
    {
        return uploadTime;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("materialId", getMaterialId())
            .append("groupId", getGroupId())
            .append("materialName", getMaterialName())
            .append("materialType", getMaterialType())
            .append("description", getDescription())
            .append("materialSize", getMaterialSize())
            .append("savePath", getSavePath())
            .append("thumbnail", getThumbnail())
            .append("auditState", getAuditState())
            .append("auditReason", getAuditReason())
            .append("useState", getUseState())
            .append("width", getWidth())
            .append("height", getHeight())
            .append("uploaderId", getUploaderId())
            .append("uploadTime", getUploadTime())
            .append("remark", getRemark())
            .toString();
    }
}
