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
import com.ruoyi.spider.domain.SpiderConfig;
import com.ruoyi.spider.service.ISpiderConfigService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 爬虫配置Controller
 * 
 * @author wujiyue
 * @date 2019-11-11
 */
@Controller
@RequestMapping("/spider/spiderConfig")
public class SpiderConfigController extends BaseController
{
    private String prefix = "spider/spiderConfig";

    @Autowired
    private ISpiderConfigService spiderConfigService;

    @RequiresPermissions("spider:spiderConfig:view")
    @GetMapping()
    public String spiderConfig()
    {
        return prefix + "/spiderConfig";
    }

    /**
     * 查询爬虫配置列表
     */
    @RequiresPermissions("spider:spiderConfig:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SpiderConfig spiderConfig)
    {
        startPage();
        List<SpiderConfig> list = spiderConfigService.selectSpiderConfigList(spiderConfig);
        return getDataTable(list);
    }

    /**
     * 导出爬虫配置列表
     */
    @RequiresPermissions("spider:spiderConfig:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SpiderConfig spiderConfig)
    {
        List<SpiderConfig> list = spiderConfigService.selectSpiderConfigList(spiderConfig);
        ExcelUtil<SpiderConfig> util = new ExcelUtil<SpiderConfig>(SpiderConfig.class);
        return util.exportExcel(list, "spiderConfig");
    }

    /**
     * 新增爬虫配置
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存爬虫配置
     */
    @RequiresPermissions("spider:spiderConfig:add")
    @Log(title = "爬虫配置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SpiderConfig spiderConfig)
    {
        SpiderConfig check=spiderConfigService.selectSpiderConfigByCode(spiderConfig.getSpiderCode());
        if(check!=null){
            return AjaxResult.error("爬虫编码["+spiderConfig.getSpiderCode()+"]已经存在!");
        }
        return toAjax(spiderConfigService.insertSpiderConfig(spiderConfig));
    }

    /**
     * 修改爬虫配置
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        SpiderConfig spiderConfig = spiderConfigService.selectSpiderConfigById(id);
        mmap.put("spiderConfig", spiderConfig);
        return prefix + "/edit";
    }

    /**
     * 修改保存爬虫配置
     */
    @RequiresPermissions("spider:spiderConfig:edit")
    @Log(title = "爬虫配置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SpiderConfig spiderConfig)
    {
        return toAjax(spiderConfigService.updateSpiderConfig(spiderConfig));
    }

    /**
     * 删除爬虫配置
     */
    @RequiresPermissions("spider:spiderConfig:remove")
    @Log(title = "爬虫配置", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(spiderConfigService.deleteSpiderConfigByIds(ids));
    }
}
