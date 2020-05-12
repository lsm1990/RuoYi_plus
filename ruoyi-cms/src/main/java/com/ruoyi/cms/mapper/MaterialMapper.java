package com.ruoyi.cms.mapper;

import com.ruoyi.cms.domain.Album;
import com.ruoyi.cms.domain.AlbumMaterial;
import com.ruoyi.cms.domain.Material;
import com.ruoyi.cms.domain.MaterialUse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 素材Mapper接口
 * 
 * @author wujiyue
 * @date 2019-11-05
 */
public interface MaterialMapper 
{
    /**
     * 查询素材
     * 
     * @param materialId 素材ID
     * @return 素材
     */
    public Material selectMaterialById(String materialId);

    /**
     * 查询素材列表
     * 
     * @param material 素材
     * @return 素材集合
     */
    public List<Material> selectMaterialList(Material material);

    /**
     * 新增素材
     * 
     * @param material 素材
     * @return 结果
     */
    public int insertMaterial(Material material);

    /**
     * 修改素材
     * 
     * @param material 素材
     * @return 结果
     */
    public int updateMaterial(Material material);

    /**
     * 删除素材
     * 
     * @param materialId 素材ID
     * @return 结果
     */
    public int deleteMaterialById(String materialId);

    /**
     * 批量删除素材
     * 
     * @param materialIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteMaterialByIds(String[] materialIds);

    /**
     * 批量审核素材
     *
     * @param materialIds 需要审核的数据ID
     * @return 结果
     */
    public int auditMaterialByIds(String[] materialIds);

    /**
     * 根据ids查询素材
     *
     * @param materialIds 需要查询数据ID
     * @return 结果
     */
    public  List<Material>  selectMaterialByIds(String[] materialIds);

    /**
     * 查询专辑关联的素材
     * @param material
     * @return
     */
    public  List<AlbumMaterial>  selectAlbumMaterialList(Material material);
    /**
     * 排除ids查询素材（查询专辑未关联的素材）
     * @param material
     * @return 结果
     */
    public List<Material> selectAlbumUnMaterialList(Material material);


    /**
     * 插入使用记录
     * @param materialUse
     * @return
     */

    public int insertMaterialUse(MaterialUse materialUse);

    /**
     * 批量插入使用记录
     * @param materialUses
     * @return
     */

    public int insertMaterialUseBatch(List<MaterialUse> materialUses);
    /**
     * 查询素材使用记录
     * @param materialUse
     * @return
     */
    public List<MaterialUse> selectMaterialUseList(MaterialUse materialUse);

    /**
     * 根据主键查询素材使用记录
     * @param ids
     * @return
     */
    public List<MaterialUse> selectMaterialUseByIds(String[] ids);
    /**
     * 批量删除素材使用记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMaterialUseBatch(String[] ids);

    /**
     * 删除素材使用；注意和上面的方法区分，有本质的不同，请看sql
     * @param materialUse
     * @return
     */
    public int deleteMaterialUse(MaterialUse materialUse);

    public int deleteMaterialUseByMx(@Param("materialId") String materialId,@Param("table") String table,@Param("useId") String  useId,@Param("column")String column);


    /**
     * 查询某个素材有几条使用记录（cms_material_use）
     * @param materialId
     * @return
     */
    public int selectCountMaterialUse(String materialId);

    /**
     * 根据素材ids查询素材是否在使用（cms_material）
     * @param materialIds
     * @return
     */
    public int selectCountUse(String[] materialIds);
    /**
     * 更新素材的使用状态
     * @param materialId
     * @param useState
     * @return
     */
    public int updateMaterialUseState(@Param("materialId") String materialId,@Param("useState")  String useState);
}
