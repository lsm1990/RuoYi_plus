package com.ruoyi.cms.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.cms.mapper.LinkMapper;
import com.ruoyi.cms.domain.Link;
import com.ruoyi.cms.service.ILinkService;
import com.ruoyi.common.core.text.Convert;

/**
 * 链接Service业务层处理
 * 
 * @author wujiyue
 * @date 2019-11-26
 */
@Service
public class LinkServiceImpl implements ILinkService 
{
    @Autowired
    private LinkMapper linkMapper;

    /**
     * 查询链接
     * 
     * @param linkId 链接ID
     * @return 链接
     */
    @Override
    public Link selectLinkById(Long linkId)
    {
        return linkMapper.selectLinkById(linkId);
    }

    /**
     * 查询链接列表
     * 
     * @param link 链接
     * @return 链接
     */
    @Override
    public List<Link> selectLinkList(Link link)
    {
        return linkMapper.selectLinkList(link);
    }

    /**
     * 新增链接
     * 
     * @param link 链接
     * @return 结果
     */
    @Override
    public int insertLink(Link link)
    {
        return linkMapper.insertLink(link);
    }

    /**
     * 修改链接
     * 
     * @param link 链接
     * @return 结果
     */
    @Override
    public int updateLink(Link link)
    {
        return linkMapper.updateLink(link);
    }

    /**
     * 删除链接对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteLinkByIds(String ids)
    {
        return linkMapper.deleteLinkByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除链接信息
     * 
     * @param linkId 链接ID
     * @return 结果
     */
    @Override
    public int deleteLinkById(Long linkId)
    {
        return linkMapper.deleteLinkById(linkId);
    }
}
