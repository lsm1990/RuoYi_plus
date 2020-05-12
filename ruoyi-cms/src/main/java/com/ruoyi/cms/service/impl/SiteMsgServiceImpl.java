package com.ruoyi.cms.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.cms.mapper.SiteMsgMapper;
import com.ruoyi.cms.domain.SiteMsg;
import com.ruoyi.cms.service.ISiteMsgService;
import com.ruoyi.common.core.text.Convert;

/**
 * 站内消息Service业务层处理
 * 
 * @author wujiyue
 * @date 2019-11-17
 */
@Service
public class SiteMsgServiceImpl implements ISiteMsgService 
{
    @Autowired
    private SiteMsgMapper siteMsgMapper;

    /**
     * 查询站内消息
     * 
     * @param id 站内消息ID
     * @return 站内消息
     */
    @Override
    public SiteMsg selectSiteMsgById(Long id)
    {
        return siteMsgMapper.selectSiteMsgById(id);
    }

    /**
     * 查询站内消息列表
     * 
     * @param siteMsg 站内消息
     * @return 站内消息
     */
    @Override
    public List<SiteMsg> selectSiteMsgList(SiteMsg siteMsg)
    {
        return siteMsgMapper.selectSiteMsgList(siteMsg);
    }

    /**
     * 新增站内消息
     * 
     * @param siteMsg 站内消息
     * @return 结果
     */
    @Override
    public int insertSiteMsg(SiteMsg siteMsg)
    {
        siteMsg.setCreateTime(DateUtils.getNowDate());
        return siteMsgMapper.insertSiteMsg(siteMsg);
    }

    /**
     * 修改站内消息
     * 
     * @param siteMsg 站内消息
     * @return 结果
     */
    @Override
    public int updateSiteMsg(SiteMsg siteMsg)
    {
        siteMsg.setUpdateTime(DateUtils.getNowDate());
        return siteMsgMapper.updateSiteMsg(siteMsg);
    }

    /**
     * 删除站内消息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSiteMsgByIds(String ids)
    {
        return siteMsgMapper.deleteSiteMsgByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除站内消息信息
     * 
     * @param id 站内消息ID
     * @return 结果
     */
    @Override
    public int deleteSiteMsgById(Long id)
    {
        return siteMsgMapper.deleteSiteMsgById(id);
    }
}
