package com.ruoyi.cms.controller;

import java.util.List;

import com.ruoyi.cms.domain.Tags;
import com.ruoyi.cms.util.CmsConstants;
import com.ruoyi.system.service.ISysConfigService;
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
import com.ruoyi.cms.domain.ArticleTemplate;
import com.ruoyi.cms.service.IArticleTemplateService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 文章模板Controller
 * 
 * @author markbro
 * @date 2019-12-31
 */
@Controller
@RequestMapping("/cms/articleTemplate")
public class ArticleTemplateController extends BaseController
{
    private String prefix = "cms/articleTemplate";

    @Autowired
    private IArticleTemplateService articleTemplateService;

    @Autowired
    private ISysConfigService configService;

    private String getEditorType(){
        return configService.selectConfigByKey(CmsConstants.KEY_EDITOR_TYPE);
    }


    @RequiresPermissions("cms:articleTemplate:view")
    @GetMapping()
    public String articleTemplate(ModelMap mmap)
    {
        String editor = getEditorType();
        if(!CmsConstants.EDITOR_TYPE_UEDITOR.equals(editor)){
            mmap.put("editorOK",false);
        }else{
            mmap.put("editorOK",true);
        }
        return prefix + "/articleTemplate";
    }

    /**
     * 查询文章模板列表
     */
    @RequiresPermissions("cms:articleTemplate:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ArticleTemplate articleTemplate)
    {
        startPage();
        List<ArticleTemplate> list = articleTemplateService.selectArticleTemplateList(articleTemplate);
        return getDataTable(list);
    }

    /**
     * 新增文章模板
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        List<Tags> tags = articleTemplateService.selectArticleTemplateTags("");
        mmap.put("tags",tags);
        return prefix + "/add";
    }

    /**
     * 新增保存文章模板
     */
    @RequiresPermissions("cms:articleTemplate:add")
    @Log(title = "文章模板", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ArticleTemplate articleTemplate)
    {
        return toAjax(articleTemplateService.insertArticleTemplate(articleTemplate));
    }

    /**
     * 修改文章模板
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ArticleTemplate articleTemplate = articleTemplateService.selectArticleTemplateById(id);
        mmap.put("articleTemplate", articleTemplate);
        List<Tags> tags = articleTemplateService.selectArticleTemplateTags(articleTemplate.getTags());
        mmap.put("tags",tags);
        return prefix + "/edit";
    }

    /**
     * 修改保存文章模板
     */
    @RequiresPermissions("cms:articleTemplate:edit")
    @Log(title = "文章模板", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ArticleTemplate articleTemplate)
    {
        return toAjax(articleTemplateService.updateArticleTemplate(articleTemplate));
    }

    /**
     * 删除文章模板
     */
    @RequiresPermissions("cms:articleTemplate:remove")
    @Log(title = "文章模板", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(articleTemplateService.deleteArticleTemplateByIds(ids));
    }
    /*#############################华丽的分割线#####################################*/
    @GetMapping("/listNew")
    public String articleTemplateList()
    {
        return prefix + "/articleTemplateList";
    }

}
