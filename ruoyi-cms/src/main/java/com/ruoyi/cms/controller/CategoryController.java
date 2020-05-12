package com.ruoyi.cms.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.cms.domain.Category;
import com.ruoyi.cms.service.ICategoryService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.domain.Ztree;

/**
 * 栏目分类Controller
 * 
 * @author wujiyue
 * @date 2019-10-28
 */
@Controller
@RequestMapping("/cms/category")
public class CategoryController extends BaseController
{
    private String prefix = "cms/category";

    @Autowired
    private ICategoryService categoryService;

    @RequiresPermissions("cms:category:view")
    @GetMapping()
    public String category()
    {
        return prefix + "/category";
    }

    /**
     * 查询栏目分类树列表
     */
    @RequiresPermissions("cms:category:list")
    @PostMapping("/list")
    @ResponseBody
    public List<Category> list(Category category)
    {
        List<Category> list = categoryService.selectCategoryList(category);
        return list;
    }

    /**
     * 导出栏目分类列表
     */
    @RequiresPermissions("cms:category:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Category category)
    {
        List<Category> list = categoryService.selectCategoryList(category);
        ExcelUtil<Category> util = new ExcelUtil<Category>(Category.class);
        return util.exportExcel(list, "category");
    }

    /**
     * 新增栏目分类
     */
    @GetMapping(value = { "/add/{categoryId}", "/add/" })
    public String add(@PathVariable(value = "categoryId", required = false) Long categoryId, ModelMap mmap)
    {
        if (StringUtils.isNotNull(categoryId))
        {
            mmap.put("category", categoryService.selectCategoryById(categoryId));
        }
        return prefix + "/add";
    }

    /**
     * 新增保存栏目分类
     */
    @RequiresPermissions("cms:category:add")
    @Log(title = "栏目分类", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Category category)
    {
        return toAjax(categoryService.insertCategory(category));
    }

    /**
     * 修改栏目分类
     */
    @GetMapping("/edit/{categoryId}")
    public String edit(@PathVariable("categoryId") Long categoryId, ModelMap mmap)
    {
        Category category = categoryService.selectCategoryById(categoryId);
        mmap.put("category", category);
        return prefix + "/edit";
    }

    /**
     * 修改保存栏目分类
     */
    @RequiresPermissions("cms:category:edit")
    @Log(title = "栏目分类", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Category category)
    {
        return toAjax(categoryService.updateCategory(category));
    }

    /**
     * 删除
     */
    @RequiresPermissions("cms:category:remove")
    @Log(title = "栏目分类", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{categoryId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("categoryId") String categoryId)
    {
        return toAjax(categoryService.deleteCategoryById(categoryId));
    }

    /**
     * 选择栏目分类树
     */
    @GetMapping(value = { "/selectCategoryTree/{categoryId}", "/selectCategoryTree/" })
    public String selectCategoryTree(@PathVariable(value = "categoryId", required = false) Long categoryId, ModelMap mmap)
    {
        if (StringUtils.isNotNull(categoryId))
        {
            mmap.put("category", categoryService.selectCategoryById(categoryId));
        }
        return prefix + "/tree";
    }

    /**
     * 加载栏目分类树列表
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = categoryService.selectCategoryTree();
        return ztrees;
    }
}
