package com.ruoyi.cms.service.impl;

import java.beans.Transient;
import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;
import com.ruoyi.cms.domain.AdMaterial;
import com.ruoyi.cms.domain.MaterialUse;
import com.ruoyi.cms.mapper.MaterialMapper;
import com.ruoyi.cms.util.CmsConstants;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.cms.mapper.AdMapper;
import com.ruoyi.cms.domain.Ad;
import com.ruoyi.cms.service.IAdService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * 广告位Service业务层处理
 * 
 * @author wujiyue
 * @date 2019-11-16
 */
@Service
public class AdServiceImpl implements IAdService 
{
    @Autowired
    private AdMapper adMapper;
    @Autowired
    private MaterialMapper materialMapper;

    /**
     * 查询广告位
     * 
     * @param adId 广告位ID
     * @return 广告位
     */
    @Override
    public Ad selectAdById(Long adId)
    {
        return adMapper.selectAdById(adId);
    }

    /**
     * 查询广告位列表
     * 
     * @param ad 广告位
     * @return 广告位
     */
    @Override
    public List<Ad> selectAdList(Ad ad)
    {
        return adMapper.selectAdList(ad);
    }

    /**
     * 新增广告位
     * 
     * @param ad 广告位
     * @return 结果
     */
    @Override
    public int insertAd(Ad ad)
    {
        ad.setCreateTime(DateUtils.getNowDate());
        return adMapper.insertAd(ad);
    }

    /**
     * 修改广告位
     * 
     * @param ad 广告位
     * @return 结果
     */
    @Override
    public int updateAd(Ad ad)
    {
        ad.setUpdateTime(DateUtils.getNowDate());
        return adMapper.updateAd(ad);
    }

    /**
     * 删除广告位对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAdByIds(String ids)
    {
        return adMapper.deleteAdByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除广告位信息
     * 
     * @param adId 广告位ID
     * @return 结果
     */
    @Override
    public int deleteAdById(Long adId)
    {
        return adMapper.deleteAdById(adId);
    }

    /*****************************************分割线****************************************************/

    @Override
    public AdMaterial selectAdMaterialById(Long id) {
        return adMapper.selectAdMaterialById(id);
    }

    @Override
    public List<AdMaterial> selectAdMaterialList(AdMaterial adMaterial) {
        return adMapper.selectAdMaterialList(adMaterial);
    }

    @Override
    @Transactional
    public int insertAdMaterial(AdMaterial adMaterial) {
        //插入使用记录 cms_material_use
        MaterialUse use=new MaterialUse();
        SysUser user= ShiroUtils.getSysUser();
            use.setUseId(adMaterial.getAdId().toString());
            use.setMaterialId(adMaterial.getMaterialId());
            use.setUserId(user.getUserId().toString());
            use.setCreateBy(user.getUserName());
            use.setCreateTime(new Date());
            use.setUserId(user.getUserId().toString());
            use.setUseTable(CmsConstants.MATERIAL_USE_AD_TABLE_NAME);
            use.setUseColumn(CmsConstants.MATERIAL_USE_AD_COLUMN_NAME);
        materialMapper.insertMaterialUse(use);
        adMaterial.setUseHisId(Integer.valueOf(use.getId()));
        //更新素材使用状态
        materialMapper.updateMaterialUseState(adMaterial.getMaterialId(),CmsConstants.USE_STATE_YES);
        return adMapper.insertAdMaterial(adMaterial);
    }

    @Override
    public int updateAdMaterial(AdMaterial adMaterial) {
        return adMapper.updateAdMaterial(adMaterial);
    }

    @Override
    public int deleteAdMaterialByIds(String ids) {
        List<AdMaterial> list=adMapper.selectAdMaterialByIds(Convert.toStrArray(ids));
        List<String> idList=Lists.newArrayList();
        for(AdMaterial material:list){
            idList.add(String.valueOf(material.getUseHisId()));
        }
        String[] useIds=idList.toArray(new String[idList.size()]);
        materialMapper.deleteMaterialUseBatch(useIds);
        for(AdMaterial material:list){
            int n=materialMapper.selectCountMaterialUse(material.getMaterialId());
            if(n==0){
                materialMapper.updateMaterialUseState(material.getMaterialId(), CmsConstants.USE_STATE_NO);
            }
        }
        return adMapper.deleteAdMaterialByIds(Convert.toStrArray(ids));
    }

    @Override
    public List<AdMaterial> selectAdUnMaterialList(AdMaterial adMaterial) {
        return adMapper.selectAdUnMaterialList(adMaterial);
    }
}
