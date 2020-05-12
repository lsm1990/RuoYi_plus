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
import com.ruoyi.spider.domain.SpiderFiledRule;
import com.ruoyi.spider.service.ISpiderFiledRuleService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 字段值处理规则Controller
 * 
 * @author wujiyue
 * @date 2019-11-14
 */
@Controller
@RequestMapping("/spider/fieldRule")
public class SpiderFiledRuleController extends BaseController
{
    private String prefix = "spider/fieldRule";

    @Autowired
    private ISpiderFiledRuleService spiderFiledRuleService;

    @RequiresPermissions("spider:fieldRule:view")
    @GetMapping("/{fieldId}")
    public String fieldRule(@PathVariable("fieldId")String fieldId,ModelMap mmap)
    {
        mmap.addAttribute("fieldId",fieldId);
        return prefix + "/fieldRule";
    }

    /**
     * 查询字段值处理规则列表
     */
    @RequiresPermissions("spider:fieldRule:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SpiderFiledRule spiderFiledRule)
    {
        startPage();
        List<SpiderFiledRule> list = spiderFiledRuleService.selectSpiderFiledRuleList(spiderFiledRule);
        return getDataTable(list);
    }

    /**
     * 新增字段值处理规则
     */
    @GetMapping("/add/{id}")
    public String add(@PathVariable("id") String id,ModelMap mmap)
    {
        mmap.put("fieldId",id);
        return prefix + "/add";
    }

    /**
     * 新增保存字段值处理规则
     */
    @RequiresPermissions("spider:fieldRule:add")
    @Log(title = "字段值处理规则", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SpiderFiledRule spiderFiledRule)
    {
        return toAjax(spiderFiledRuleService.insertSpiderFiledRule(spiderFiledRule));
    }

    /**
     * 修改字段值处理规则
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        SpiderFiledRule spiderFiledRule = spiderFiledRuleService.selectSpiderFiledRuleById(id);
        mmap.put("spiderFiledRule", spiderFiledRule);
        return prefix + "/edit";
    }

    /**
     * 修改保存字段值处理规则
     */
    @RequiresPermissions("spider:fieldRule:edit")
    @Log(title = "字段值处理规则", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SpiderFiledRule spiderFiledRule)
    {
        return toAjax(spiderFiledRuleService.updateSpiderFiledRule(spiderFiledRule));
    }

    /**
     * 删除字段值处理规则
     */
    @RequiresPermissions("spider:fieldRule:remove")
    @Log(title = "字段值处理规则", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(spiderFiledRuleService.deleteSpiderFiledRuleByIds(ids));
    }
}
