package com.ruoyi.cms.service;

import com.ruoyi.cms.domain.Material;
import com.ruoyi.cms.domain.MaterialUse;

import java.util.List;

/**
 * 素材Service接口
 * 
 * @author wujiyue
 * @date 2019-11-05
 */
public interface IMaterialService 
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
     * 批量删除素材
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMaterialByIds(String ids);

    /**
     * 删除素材信息
     * 
     * @param materialId 素材ID
     * @return 结果
     */
    public int deleteMaterialById(String materialId);

    /**
     * 批量审核素材
     *
     * @param ids 需要审核的数据ID
     * @return 结果
     */
    public int auditMaterialByIds(String ids);

    /**
     * 查询素材使用记录
     * @param materialUse
     * @return
     */
    public List<MaterialUse> selectMaterialUseList(MaterialUse materialUse);

    /**
     * 1.删除素材使用记录，2.更新素材表use_state字段 3删除使用地方的表的真实使用记录
     * @param materialUse
     */
    public void deleteMaterialUseBatch(MaterialUse materialUse);
}
