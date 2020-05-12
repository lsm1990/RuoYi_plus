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
import com.ruoyi.cms.domain.Template;
import com.ruoyi.cms.service.ITemplateService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 模板Controller
 * 
 * @author wujiyue
 * @date 2019-11-17
 */
@Controller
@RequestMapping("/cms/template")
public class TemplateController extends BaseController
{
    private String prefix = "cms/template";

    @Autowired
    private ITemplateService templateService;

    @RequiresPermissions("cms:template:view")
    @GetMapping()
    public String template()
    {
        return prefix + "/template";
    }

    /**
     * 查询模板列表
     */
    @RequiresPermissions("cms:template:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Template template)
    {
        startPage();
        List<Template> list = templateService.selectTemplateList(template);
        return getDataTable(list);
    }

    /**
     * 导出模板列表
     */
    @RequiresPermissions("cms:template:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Template template)
    {
        List<Template> list = templateService.selectTemplateList(template);
        ExcelUtil<Template> util = new ExcelUtil<Template>(Template.class);
        return util.exportExcel(list, "template");
    }

    /**
     * 新增模板
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存模板
     */
    @RequiresPermissions("cms:template:add")
    @Log(title = "模板", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Template template)
    {
        return toAjax(templateService.insertTemplate(template));
    }

    /**
     * 修改模板
     */
    @GetMapping("/edit/{templateId}")
    public String edit(@PathVariable("templateId") Long templateId, ModelMap mmap)
    {
        Template template = templateService.selectTemplateById(templateId);
        mmap.put("template", template);
        return prefix + "/edit";
    }

    /**
     * 修改保存模板
     */
    @RequiresPermissions("cms:template:edit")
    @Log(title = "模板", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Template template)
    {
        return toAjax(templateService.updateTemplate(template));
    }

    /**
     * 删除模板
     */
    @RequiresPermissions("cms:template:remove")
    @Log(title = "模板", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(templateService.deleteTemplateByIds(ids));
    }
}
