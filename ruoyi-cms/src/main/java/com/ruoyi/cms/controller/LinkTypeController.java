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
import com.ruoyi.cms.domain.LinkType;
import com.ruoyi.cms.service.ILinkTypeService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 链接分类Controller
 * 
 * @author wujiyue
 * @date 2019-11-26
 */
@Controller
@RequestMapping("/cms/linkType")
public class LinkTypeController extends BaseController
{
    private String prefix = "cms/linkType";

    @Autowired
    private ILinkTypeService linkTypeService;

    @RequiresPermissions("cms:linkType:view")
    @GetMapping()
    public String linkType()
    {
        return prefix + "/linkType";
    }

    /**
     * 查询链接分类列表
     */
    @RequiresPermissions("cms:linkType:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(LinkType linkType)
    {
        startPage();
        List<LinkType> list = linkTypeService.selectLinkTypeList(linkType);
        return getDataTable(list);
    }

    /**
     * 导出链接分类列表
     */
    @RequiresPermissions("cms:linkType:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(LinkType linkType)
    {
        List<LinkType> list = linkTypeService.selectLinkTypeList(linkType);
        ExcelUtil<LinkType> util = new ExcelUtil<LinkType>(LinkType.class);
        return util.exportExcel(list, "linkType");
    }

    /**
     * 新增链接分类
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存链接分类
     */
    @RequiresPermissions("cms:linkType:add")
    @Log(title = "链接分类", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(LinkType linkType)
    {
        return toAjax(linkTypeService.insertLinkType(linkType));
    }

    /**
     * 修改链接分类
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        LinkType linkType = linkTypeService.selectLinkTypeById(id);
        mmap.put("linkType", linkType);
        return prefix + "/edit";
    }

    /**
     * 修改保存链接分类
     */
    @RequiresPermissions("cms:linkType:edit")
    @Log(title = "链接分类", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(LinkType linkType)
    {
        return toAjax(linkTypeService.updateLinkType(linkType));
    }

    /**
     * 删除链接分类
     */
    @RequiresPermissions("cms:linkType:remove")
    @Log(title = "链接分类", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(linkTypeService.deleteLinkTypeByIds(ids));
    }
}
