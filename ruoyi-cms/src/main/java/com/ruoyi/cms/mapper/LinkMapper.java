package com.ruoyi.cms.mapper;

import com.ruoyi.cms.domain.Link;
import java.util.List;

/**
 * 链接Mapper接口
 * 
 * @author wujiyue
 * @date 2019-11-26
 */
public interface LinkMapper 
{
    /**
     * 查询链接
     * 
     * @param linkId 链接ID
     * @return 链接
     */
    public Link selectLinkById(Long linkId);

    /**
     * 查询链接列表
     * 
     * @param link 链接
     * @return 链接集合
     */
    public List<Link> selectLinkList(Link link);

    /**
     * 新增链接
     * 
     * @param link 链接
     * @return 结果
     */
    public int insertLink(Link link);

    /**
     * 修改链接
     * 
     * @param link 链接
     * @return 结果
     */
    public int updateLink(Link link);

    /**
     * 删除链接
     * 
     * @param linkId 链接ID
     * @return 结果
     */
    public int deleteLinkById(Long linkId);

    /**
     * 批量删除链接
     * 
     * @param linkIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteLinkByIds(String[] linkIds);
}
