package com.ruoyi.cms.service;

import com.ruoyi.cms.domain.FriendLink;
import java.util.List;

/**
 * 友情链接Service接口
 * 
 * @author wujiyue
 * @date 2019-11-16
 */
public interface IFriendLinkService 
{
    /**
     * 查询友情链接
     * 
     * @param id 友情链接ID
     * @return 友情链接
     */
    public FriendLink selectFriendLinkById(Long id);

    /**
     * 查询友情链接列表
     * 
     * @param friendLink 友情链接
     * @return 友情链接集合
     */
    public List<FriendLink> selectFriendLinkList(FriendLink friendLink);

    /**
     * 新增友情链接
     * 
     * @param friendLink 友情链接
     * @return 结果
     */
    public int insertFriendLink(FriendLink friendLink);

    /**
     * 修改友情链接
     * 
     * @param friendLink 友情链接
     * @return 结果
     */
    public int updateFriendLink(FriendLink friendLink);

    /**
     * 批量删除友情链接
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFriendLinkByIds(String ids);

    /**
     * 删除友情链接信息
     * 
     * @param id 友情链接ID
     * @return 结果
     */
    public int deleteFriendLinkById(Long id);
}
