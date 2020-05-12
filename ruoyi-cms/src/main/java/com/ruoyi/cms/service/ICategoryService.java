package com.ruoyi.cms.service;

import com.ruoyi.cms.domain.Category;
import java.util.List;
import com.ruoyi.common.core.domain.Ztree;

/**
 * 栏目分类Service接口
 * 
 * @author wujiyue
 * @date 2019-10-28
 */
public interface ICategoryService 
{
    /**
     * 查询栏目分类
     * 
     * @param categoryId 栏目分类ID
     * @return 栏目分类
     */
    public Category selectCategoryById(Long categoryId);

    /**
     * 查询栏目分类列表
     * 
     * @param category 栏目分类
     * @return 栏目分类集合
     */
    public List<Category> selectCategoryList(Category category);

    /**
     * 新增栏目分类
     * 
     * @param category 栏目分类
     * @return 结果
     */
    public int insertCategory(Category category);

    /**
     * 修改栏目分类
     * 
     * @param category 栏目分类
     * @return 结果
     */
    public int updateCategory(Category category);

    /**
     * 批量删除栏目分类
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCategoryByIds(String ids);

    /**
     * 删除栏目分类信息
     * 
     * @param categoryId 栏目分类ID
     * @return 结果
     */
    public int deleteCategoryById(String categoryId);

    /**
     * 查询栏目分类树列表
     * 
     * @return 所有栏目分类信息
     */
    public List<Ztree> selectCategoryTree();

    /**
     * 查询导航栏目
     * @param category
     * @return
     */
    public List<Category> selectNavCategories(Category category);
}
