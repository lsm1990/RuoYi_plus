package com.ruoyi.cms.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.List;

/**
 * 素材相册对象 cms_album
 * 
 * @author wujiyue
 * @date 2019-11-08
 */
public class Album extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 相册ID */
    private String albumId;

    /** 相册名称 */
    @Excel(name = "相册名称")
    private String albumName;

    /** 创建人ID */
    private String userId;

    /** 部门ID */
    private String deptId;

    /** 相册类型 */
    @Excel(name = "相册类型")
    private String albumType;


    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** 审核状态 */
    @Excel(name = "审核状态")
    private Integer auditState;
    @Excel(name = "编码")
    private String code;
    @Excel(name = "宽度")
    private Integer width;
    @Excel(name = "高度")
    private Integer height;

    List<AlbumMaterial> images;

    public void setAlbumId(String albumId)
    {
        this.albumId = albumId;
    }

    public String getAlbumId() 
    {
        return albumId;
    }
    public void setAlbumName(String albumName) 
    {
        this.albumName = albumName;
    }

    public String getAlbumName() 
    {
        return albumName;
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
    public void setAlbumType(String albumType) 
    {
        this.albumType = albumType;
    }

    public String getAlbumType() 
    {
        return albumType;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setAuditState(Integer auditState) 
    {
        this.auditState = auditState;
    }

    public Integer getAuditState() 
    {
        return auditState;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public List<AlbumMaterial> getImages() {
        return images;
    }

    public void setImages(List<AlbumMaterial> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("albumId", getAlbumId())
            .append("albumName", getAlbumName())
            .append("userId", getUserId())
            .append("deptId", getDeptId())
            .append("albumType", getAlbumType())
            .append("description", getDescription())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("auditState", getAuditState())
            .toString();
    }
}
