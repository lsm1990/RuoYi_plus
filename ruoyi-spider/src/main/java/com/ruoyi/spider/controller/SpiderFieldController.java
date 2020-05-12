package com.ruoyi.spider.controller;

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
import com.ruoyi.spider.domain.SpiderField;
import com.ruoyi.spider.service.ISpiderFieldService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 爬虫字段Controller
 * 
 * @author wujiyue
 * @date 2019-11-12
 */
@Controller
@RequestMapping("/spider/spiderField")
public class SpiderFieldController extends BaseController
{
    private String prefix = "spider/spiderField";

    @Autowired
    private ISpiderFieldService spiderFieldService;

    @RequiresPermissions("spider:spiderField:view")
    @GetMapping("/{configId}")
    public String spiderField(@PathVariable("configId")String configId,ModelMap mmap)
    {
        mmap.addAttribute("configId",configId);
        return prefix + "/spiderField";
    }

    /**
     * 查询爬虫字段列表
     */
    @RequiresPermissions("spider:spiderField:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SpiderField spiderField)
    {
        startPage();
        List<SpiderField> list = spiderFieldService.selectSpiderFieldList(spiderField);
        return getDataTable(list);
    }

    /**
     * 导出爬虫字段列表
     */
    @RequiresPermissions("spider:spiderField:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SpiderField spiderField)
    {
        List<SpiderField> list = spiderFieldService.selectSpiderFieldList(spiderField);
        ExcelUtil<SpiderField> util = new ExcelUtil<SpiderField>(SpiderField.class);
        return util.exportExcel(list, "spiderField");
    }

    /**
     * 新增爬虫字段
     */
    @GetMapping("/add/{id}")
    public String add(@PathVariable("id") String id,ModelMap mmap)
    {
        String configId=id;
        mmap.addAttribute("configId",configId);
        return prefix + "/add";
    }

    /**
     * 新增保存爬虫字段
     */
    @RequiresPermissions("spider:spiderField:add")
    @Log(title = "爬虫字段", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SpiderField spiderField)
    {
        return toAjax(spiderFieldService.insertSpiderField(spiderField));
    }

    /**
     * 修改爬虫字段
     */
    @GetMapping("/edit/{fieldId}")
    public String edit(@PathVariable("fieldId") Long fieldId, ModelMap mmap)
    {
        SpiderField spiderField = spiderFieldService.selectSpiderFieldById(fieldId);
        mmap.put("spiderField", spiderField);
        return prefix + "/edit";
    }

    /**
     * 修改保存爬虫字段
     */
    @RequiresPermissions("spider:spiderField:edit")
    @Log(title = "爬虫字段", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SpiderField spiderField)
    {
        return toAjax(spiderFieldService.updateSpiderField(spiderField));
    }

    /**
     * 删除爬虫字段
     */
    @RequiresPermissions("spider:spiderField:remove")
    @Log(title = "爬虫字段", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(spiderFieldService.deleteSpiderFieldByIds(ids));
    }
}
