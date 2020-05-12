package com.ruoyi.cms.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 资源对象 cms_resource
 * 
 * @author wujiyue
 * @date 2019-11-23
 */
public class Resource extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 资源ID */
    private String id;

    private String resourceRegion;
    /** 资源名称 */
    @Excel(name = "资源名称")
    private String title;

    /** 资源类型 */
    @Excel(name = "资源类型")
    private String type;

    /** 资源描述 */
    private String description;

    /** 详情 */
    private String detail;

    /** 资源大小 */
    @Excel(name = "资源大小")
    private String size;

    /** 保存路径 */
    private String savePath;

    /** 缩略图 */
    @Excel(name = "缩略图")
    private String coverImage;

    /** 审核状态 */
    @Excel(name = "审核状态")
    private String auditState;

    /** 评分 */
    @Excel(name = "评分")
    private Integer score;

    /** 下载类型 */
    @Excel(name = "下载类型")
    private String downloadType;

    /** 收藏数 */
    @Excel(name = "收藏数")
    private Long favouriteCount;

    /** 下载次数 */
    @Excel(name = "下载次数")
    private Long downloadCount;
    /** 点击次数 */
    @Excel(name = "点击次数")
    private Long hit;
    /** 点赞数 */
    @Excel(name = "点赞数")
    private Long upVote;

    /** 付费 */
    @Excel(name = "付费")
    private Integer free;

    /** 付费类型 */
    @Excel(name = "付费类型")
    private String payType;

    /** 花费 */
    @Excel(name = "花费")
    private Long cost;

    /** 标签 */
    @Excel(name = "标签")
    private String tagIds;

    /** 上传人用户ID */
    private String userId;

    /** 上传人 */
    private String userName;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;
    private String extraName;//扩展字段
    public String getResourceRegion() {
        return resourceRegion;
    }

    public void setResourceRegion(String resourceRegion) {
        this.resourceRegion = resourceRegion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExtraName() {
        return extraName;
    }

    public void setExtraName(String extraName) {
        this.extraName = extraName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getHit() {
        return hit;
    }

    public void setHit(Long hit) {
        this.hit = hit;
    }


    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setDetail(String detail) 
    {
        this.detail = detail;
    }

    public String getDetail() 
    {
        return detail;
    }
    public void setSize(String size) 
    {
        this.size = size;
    }

    public String getSize() 
    {
        return size;
    }
    public void setSavePath(String savePath) 
    {
        this.savePath = savePath;
    }

    public String getSavePath() 
    {
        return savePath;
    }
    public void setCoverImage(String coverImage) 
    {
        this.coverImage = coverImage;
    }

    public String getCoverImage() 
    {
        return coverImage;
    }
    public void setAuditState(String auditState) 
    {
        this.auditState = auditState;
    }

    public String getAuditState() 
    {
        return auditState;
    }
    public void setScore(Integer score) 
    {
        this.score = score;
    }

    public Integer getScore() 
    {
        return score;
    }
    public void setDownloadType(String downloadType) 
    {
        this.downloadType = downloadType;
    }

    public String getDownloadType() 
    {
        return downloadType;
    }
    public void setFavouriteCount(Long favouriteCount) 
    {
        this.favouriteCount = favouriteCount;
    }

    public Long getFavouriteCount() 
    {
        return favouriteCount;
    }
    public void setDownloadCount(Long downloadCount) 
    {
        this.downloadCount = downloadCount;
    }

    public Long getDownloadCount() 
    {
        return downloadCount;
    }
    public void setUpVote(Long upVote) 
    {
        this.upVote = upVote;
    }

    public Long getUpVote() 
    {
        return upVote;
    }
    public void setFree(Integer free) 
    {
        this.free = free;
    }

    public Integer getFree() 
    {
        return free;
    }
    public void setPayType(String payType) 
    {
        this.payType = payType;
    }

    public String getPayType() 
    {
        return payType;
    }
    public void setCost(Long cost) 
    {
        this.cost = cost;
    }

    public Long getCost() 
    {
        return cost;
    }
    public void setTagIds(String tagIds) 
    {
        this.tagIds = tagIds;
    }

    public String getTagIds() 
    {
        return tagIds;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("type", getType())
            .append("description", getDescription())
            .append("detail", getDetail())
            .append("size", getSize())
            .append("savePath", getSavePath())
            .append("coverImage", getCoverImage())
            .append("auditState", getAuditState())
            .append("score", getScore())
            .append("downloadType", getDownloadType())
            .append("favouriteCount", getFavouriteCount())
            .append("downloadCount", getDownloadCount())
            .append("upVote", getUpVote())
            .append("free", getFree())
            .append("payType", getPayType())
            .append("cost", getCost())
            .append("tagIds", getTagIds())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("status", getStatus())
            .append("remark", getRemark())
            .toString();
    }
}
