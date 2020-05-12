package com.ruoyi.cms.domain;

/**
 * 相册素材实体类
 */
public class AlbumMaterial extends Material{

    private Long id;

    /** link */
    private String link;
    /** sort */
    private String sort;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }


}
