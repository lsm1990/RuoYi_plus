package com.ruoyi.cms.mapper;

import com.ruoyi.cms.domain.MaterialGroup;
import java.util.List;

/**
 * 素材分组Mapper接口
 * 
 * @author wujiyue
 * @date 2019-11-07
 */
public interface MaterialGroupMapper 
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
     * 删除素材分组
     * 
     * @param groupId 素材分组ID
     * @return 结果
     */
    public int deleteMaterialGroupById(Long groupId);

    /**
     * 批量删除素材分组
     * 
     * @param groupIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteMaterialGroupByIds(String[] groupIds);
}
