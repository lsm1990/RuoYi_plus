package com.ruoyi.cms.domain;

/**
 * 素材使用记录实体类
 */
public class MaterialUse extends Material{
    /*主键ID*/
    private String id;
    /*素材在那张表中使用*/
    private String useTable;
    /*比如cms_album_material表的album_id字段的列名“album_id”*/
    private String  useColumn;
    /*比如cms_album_material表的album_id字段*/
    private String  useId;
    /*创建人ID*/
    private String  userId;

    private String ids;//扩展字段

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUseTable() {
        return useTable;
    }

    public void setUseTable(String useTable) {
        this.useTable = useTable;
    }

    public String getUseId() {
        return useId;
    }

    public void setUseId(String useId) {
        this.useId = useId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUseColumn() {
        return useColumn;
    }

    public void setUseColumn(String useColumn) {
        this.useColumn = useColumn;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return "MaterialUse{" +
                "id='" + id + '\'' +
                ", useTable='" + useTable + '\'' +
                ", useId='" + useId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
