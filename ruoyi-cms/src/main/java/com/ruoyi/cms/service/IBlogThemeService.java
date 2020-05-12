package com.ruoyi.cms.service;

import com.ruoyi.cms.domain.BlogTheme;
import java.util.List;

/**
 * 博客主题Service接口
 * 
 * @author ruoyi
 * @date 2019-12-20
 */
public interface IBlogThemeService 
{
    /**
     * 查询博客主题
     * 
     * @param id 博客主题ID
     * @return 博客主题
     */
    public BlogTheme selectBlogThemeById(Long id);

    /**
     * 查询博客主题列表
     * 
     * @param blogTheme 博客主题
     * @return 博客主题集合
     */
    public List<BlogTheme> selectBlogThemeList(BlogTheme blogTheme);

    /**
     * 新增博客主题
     * 
     * @param blogTheme 博客主题
     * @return 结果
     */
    public int insertBlogTheme(BlogTheme blogTheme);

    /**
     * 修改博客主题
     * 
     * @param blogTheme 博客主题
     * @return 结果
     */
    public int updateBlogTheme(BlogTheme blogTheme);

    /**
     * 批量删除博客主题
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBlogThemeByIds(String ids);

    /**
     * 删除博客主题信息
     * 
     * @param id 博客主题ID
     * @return 结果
     */
    public int deleteBlogThemeById(Long id);

    /**
     * 更新博客主题配置
     * @param theme
     * @return
     */
    public int updateBlogThemeConfig(String theme);

    /**
     * 查询当前博客主题
     * @return
     */
    public String queryCurrentBlogTheme();
}
