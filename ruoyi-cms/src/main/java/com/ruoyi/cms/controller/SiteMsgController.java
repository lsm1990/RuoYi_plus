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
import com.ruoyi.cms.domain.SiteMsg;
import com.ruoyi.cms.service.ISiteMsgService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 站内消息Controller
 * 
 * @author wujiyue
 * @date 2019-11-17
 */
@Controller
@RequestMapping("/cms/siteMsg")
public class SiteMsgController extends BaseController
{
    private String prefix = "cms/siteMsg";

    @Autowired
    private ISiteMsgService siteMsgService;

    @RequiresPermissions("cms:siteMsg:view")
    @GetMapping()
    public String siteMsg()
    {
        return prefix + "/siteMsg";
    }

    /**
     * 查询站内消息列表
     */
    @RequiresPermissions("cms:siteMsg:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SiteMsg siteMsg)
    {
        startPage();
        List<SiteMsg> list = siteMsgService.selectSiteMsgList(siteMsg);
        return getDataTable(list);
    }

    /**
     * 新增站内消息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存站内消息
     */
    @RequiresPermissions("cms:siteMsg:add")
    @Log(title = "站内消息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SiteMsg siteMsg)
    {
        return toAjax(siteMsgService.insertSiteMsg(siteMsg));
    }

    /**
     * 修改站内消息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        SiteMsg siteMsg = siteMsgService.selectSiteMsgById(id);
        mmap.put("siteMsg", siteMsg);
        return prefix + "/edit";
    }

    /**
     * 修改保存站内消息
     */
    @RequiresPermissions("cms:siteMsg:edit")
    @Log(title = "站内消息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SiteMsg siteMsg)
    {
        return toAjax(siteMsgService.updateSiteMsg(siteMsg));
    }

    /**
     * 删除站内消息
     */
    @RequiresPermissions("cms:siteMsg:remove")
    @Log(title = "站内消息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(siteMsgService.deleteSiteMsgByIds(ids));
    }
}
