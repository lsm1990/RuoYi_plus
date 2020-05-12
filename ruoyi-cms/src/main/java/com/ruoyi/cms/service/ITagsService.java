package com.ruoyi.cms.service;

import com.ruoyi.cms.domain.Tags;
import java.util.List;

/**
 * 标签管理Service接口
 * 
 * @author wujiyue
 * @date 2019-10-29
 */
public interface ITagsService 
{
    /**
     * 查询标签管理
     * 
     * @param tagId 标签管理ID
     * @return 标签管理
     */
    public Tags selectTagsById(Long tagId);

    /**
     * 根据标签类型和名称查询标签
     * @param type 标签类型
     * @param name 标签名称
     * @return 标签管理
     */
    public Tags getTagByName(String type,String name);

    /**
     * 查询标签管理列表
     * 
     * @param tags 标签管理
     * @return 标签管理集合
     */
    public List<Tags> selectTagsList(Tags tags);

    /**
     * 新增标签管理
     * 
     * @param tags 标签管理
     * @return 结果
     */
    public int insertTags(Tags tags);

    /**
     * 修改标签管理
     * 
     * @param tags 标签管理
     * @return 结果
     */
    public int updateTags(Tags tags);

    /**
     * 批量删除标签管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTagsByIds(String ids);

    /**
     * 删除标签管理信息
     * 
     * @param tagId 标签管理ID
     * @return 结果
     */
    public int deleteTagsById(Long tagId);

    /**
     * 查询所有标签展示到页面
     * @return
     */
    public List<Tags> selectTagsAll();

    /**
     * 查询所有标签并标记选中的标签
     * @return
     */
    public List<Tags> selectSelectedTagsAll(String selectedIds);

    /**
     * 查询前台页面fullTabs标签
     * @return
     */
    public  List<Tags> selectBlogTabs();
}
