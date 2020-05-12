package com.ruoyi.cms.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.cms.domain.MaterialUse;
import com.ruoyi.cms.util.CmsConstants;
import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.utils.Guid;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.cms.mapper.MaterialMapper;
import com.ruoyi.cms.domain.Material;
import com.ruoyi.cms.service.IMaterialService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * 素材Service业务层处理
 * 
 * @author wujiyue
 * @date 2019-11-05
 */
@Service
public class MaterialServiceImpl implements IMaterialService 
{
    @Autowired
    private MaterialMapper materialMapper;

    /**
     * 查询素材
     * 
     * @param materialId 素材ID
     * @return 素材
     */
    @Override
    public Material selectMaterialById(String materialId)
    {
        return materialMapper.selectMaterialById(materialId);
    }

    /**
     * 查询素材列表
     * 
     * @param material 素材
     * @return 素材
     */
    @Override
    @DataScope(deptAlias = "b",userAlias = "b")
    public List<Material> selectMaterialList(Material material)
    {
        SysUser user=ShiroUtils.getSysUser();
        if(!user.isAdmin()){
            //不是管理员只能查询该部门及以下分组的素材
            material.setDeptId(user.getDeptId());
        }
        return materialMapper.selectMaterialList(material);
    }

    /**
     * 新增素材
     * 
     * @param material 素材
     * @return 结果
     */
    @Override
    public int insertMaterial(Material material)
    {
        material.setMaterialId(Guid.get());
        SysUser user= ShiroUtils.getSysUser();
        material.setUploaderId(user.getUserId().toString());
        material.setUploadTime(new Date());
        //新增的素材自然是没有使用过
        material.setUseState(CmsConstants.USE_STATE_NO);
        return materialMapper.insertMaterial(material);
    }

    /**
     * 修改素材
     * 
     * @param material 素材
     * @return 结果
     */
    @Override
    public int updateMaterial(Material material)
    {
        return materialMapper.updateMaterial(material);
    }

    /**
     * 删除素材对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMaterialByIds(String ids)
    {
        String[] arr=Convert.toStrArray(ids);
        int count=materialMapper.selectCountUse(arr);
        if(count>0){
            return -1;
        }
        for(String id:arr){
           Material m= this.selectMaterialById(id);
           if(m!=null){
               try{
                   FileUploadUtils.deleteFile(m.getSavePath());
               }catch (Exception ex){}
           }
        }
        return materialMapper.deleteMaterialByIds(arr);
    }

    /**
     * 删除素材信息
     * 
     * @param materialId 素材ID
     * @return 结果
     */
    @Override
    public int deleteMaterialById(String materialId)
    {
        return materialMapper.deleteMaterialById(materialId);
    }

    @Override
    public int auditMaterialByIds(String ids) {
        String[] arr=Convert.toStrArray(ids);
        return materialMapper.auditMaterialByIds(arr);
    }

    @Override
    public List<MaterialUse> selectMaterialUseList(MaterialUse materialUse) {
        return materialMapper.selectMaterialUseList(materialUse);
    }

    @Override
    @Transactional
    public void deleteMaterialUseBatch(MaterialUse materialUse) {
        String[] arr=Convert.toStrArray(materialUse.getIds());

        List<MaterialUse> materialUses=materialMapper.selectMaterialUseByIds(arr);
        if(CollectionUtils.isNotEmpty(materialUses)){
            for(MaterialUse temp:materialUses){
                materialMapper.deleteMaterialUse(temp);
            }
        }
        materialMapper.deleteMaterialUseBatch(arr);
        int n=materialMapper.selectCountMaterialUse(materialUse.getMaterialId());
        if(n==0){
             materialMapper.updateMaterialUseState(materialUse.getMaterialId(), CmsConstants.USE_STATE_NO);
        }
    }
}
