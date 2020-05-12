package com.ruoyi.cms.service.impl;

import java.util.List;
import java.util.ArrayList;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.cms.mapper.MaterialGroupMapper;
import com.ruoyi.cms.domain.MaterialGroup;
import com.ruoyi.cms.service.IMaterialGroupService;
import com.ruoyi.common.core.text.Convert;

/**
 * 素材分组Service业务层处理
 * 
 * @author wujiyue
 * @date 2019-11-07
 */
@Service
public class MaterialGroupServiceImpl implements IMaterialGroupService 
{
    @Autowired
    private MaterialGroupMapper materialGroupMapper;

    /**
     * 查询素材分组
     * 
     * @param groupId 素材分组ID
     * @return 素材分组
     */
    @Override
    public MaterialGroup selectMaterialGroupById(Long groupId)
    {
        return materialGroupMapper.selectMaterialGroupById(groupId);
    }

    /**
     * 查询素材分组列表
     * 
     * @param materialGroup 素材分组
     * @return 素材分组
     */
    @Override
    public List<MaterialGroup> selectMaterialGroupList(MaterialGroup materialGroup)
    {
        return materialGroupMapper.selectMaterialGroupList(materialGroup);
    }

    /**
     * 新增素材分组
     * 
     * @param materialGroup 素材分组
     * @return 结果
     */
    @Override
    public int insertMaterialGroup(MaterialGroup materialGroup)
    {
        SysUser user= ShiroUtils.getSysUser();
        materialGroup.setUserId(user.getUserId().toString());
        materialGroup.setCreateBy(user.getUserName());
        materialGroup.setDeptId(user.getDeptId().toString());
        materialGroup.setCreateTime(DateUtils.getNowDate());
        return materialGroupMapper.insertMaterialGroup(materialGroup);
    }

    /**
     * 修改素材分组
     * 
     * @param materialGroup 素材分组
     * @return 结果
     */
    @Override
    public int updateMaterialGroup(MaterialGroup materialGroup)
    {
        materialGroup.setUpdateTime(DateUtils.getNowDate());
        return materialGroupMapper.updateMaterialGroup(materialGroup);
    }

    /**
     * 删除素材分组对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMaterialGroupByIds(String ids)
    {
        return materialGroupMapper.deleteMaterialGroupByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除素材分组信息
     * 
     * @param groupId 素材分组ID
     * @return 结果
     */
    @Override
    public int deleteMaterialGroupById(Long groupId)
    {
        return materialGroupMapper.deleteMaterialGroupById(groupId);
    }

    /**
     * 查询素材分组树列表
     * 
     * @return 所有素材分组信息
     */
    @Override
    public List<Ztree> selectMaterialGroupTree()
    {
        List<MaterialGroup> materialGroupList = materialGroupMapper.selectMaterialGroupList(new MaterialGroup());
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (MaterialGroup materialGroup : materialGroupList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(materialGroup.getGroupId());
            ztree.setpId(materialGroup.getParentId());
            ztree.setName(materialGroup.getGroupName());
            ztree.setTitle(materialGroup.getGroupName());
            ztrees.add(ztree);
        }
        return ztrees;
    }
}
