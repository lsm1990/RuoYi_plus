package com.ruoyi.cms.domain;

import java.util.Date;

/**
 * 广告素材
 */
public class AdMaterial extends Material{
    /*主键*/
    private Integer id;
    /*广告位ID*/
    private Integer adId;
    /*链接*/
    private String link;
    /*排序*/
    private int sort;
    /*点击数*/
    private int hit;
    /*开始时间*/
    private Date startTime;
    /*结束时间*/
    private Date endTime;
    /*状态*/
    private int status;
    private int useHisId;

    public int getUseHisId() {
        return useHisId;
    }

    public void setUseHisId(int useHisId) {
        this.useHisId = useHisId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdId() {
        return adId;
    }

    public void setAdId(Integer adId) {
        this.adId = adId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
