package com.ruoyi.cms.service;

import com.ruoyi.cms.domain.MaterialGroup;
import java.util.List;
import com.ruoyi.common.core.domain.Ztree;

/**
 * 素材分组Service接口
 * 
 * @author wujiyue
 * @date 2019-11-07
 */
public interface IMaterialGroupService 
{
    /**
     * 查询素材分组
     * 
     * @param groupId 素材分组ID
     * @return 素材分组
     */
    public MaterialGroup selectMaterialGroupById(Long groupId);

    /**
     * 查询素材分组列表
     * 
     * @param materialGroup 素材分组
     * @return 素材分组集合
     */
    public List<MaterialGroup> selectMaterialGroupList(MaterialGroup materialGroup);

    /**
     * 新增素材分组
     * 
     * @param materialGroup 素材分组
     * @return 结果
     */
    public int insertMaterialGroup(MaterialGroup materialGroup);

    /**
     * 修改素材分组
     * 
     * @param materialGroup 素材分组
     * @return 结果
     */
    public int updateMaterialGroup(MaterialGroup materialGroup);

    /**
     * 批量删除素材分组
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMaterialGroupByIds(String ids);

    /**
     * 删除素材分组信息
     * 
     * @param groupId 素材分组ID
     * @return 结果
     */
    public int deleteMaterialGroupById(Long groupId);

    /**
     * 查询素材分组树列表
     * 
     * @return 所有素材分组信息
     */
    public List<Ztree> selectMaterialGroupTree();
}
