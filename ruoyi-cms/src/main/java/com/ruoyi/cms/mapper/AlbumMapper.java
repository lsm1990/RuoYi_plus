package com.ruoyi.cms.mapper;

import com.ruoyi.cms.domain.Album;
import com.ruoyi.cms.domain.AlbumMaterial;

import java.util.List;

/**
 * 相册Mapper接口
 * 
 * @author wujiyue
 * @date 2019-11-08
 */
public interface AlbumMapper 
{
    /**
     * 查询专辑
     * 
     * @param albumId 专辑ID
     * @return 专辑
     */
    public Album selectAlbumById(String albumId);

    /**
     * 查询专辑
     *
     * @param code 专辑编码
     * @return 专辑
     */
    public Album selectAlbumByCode(String code);

    /**
     * 查询专辑列表
     * 
     * @param album 专辑
     * @return 专辑集合
     */
    public List<Album> selectAlbumList(Album album);

    /**
     * 新增专辑
     * 
     * @param album 专辑
     * @return 结果
     */
    public int insertAlbum(Album album);
    /**
     * 插入专辑素材
     *
     * @param albumMaterial 专辑素材
     * @return 结果
     */
    public int insertAlbumMaterial(AlbumMaterial albumMaterial);
    /**
     * 批量插入专辑素材
     *
     * @param albumMaterials 专辑素材
     * @return 结果
     */
    public int insertAlbumMaterialBatch(List<AlbumMaterial> albumMaterials);
    /**
     * 修改专辑
     * 
     * @param album 专辑
     * @return 结果
     */
    public int updateAlbum(Album album);

    /**
     * 删除专辑
     * 
     * @param albumId 专辑ID
     * @return 结果
     */
    public int deleteAlbumById(String albumId);

    /**
     * 批量删除专辑
     * 
     * @param albumIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteAlbumByIds(String[] albumIds);

    /**
     * 批量删除专辑素材
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAlbumMaterialByIds(String[] ids);


    public List<AlbumMaterial> selectAlbumMaterialByIds(String[] ids);
}
