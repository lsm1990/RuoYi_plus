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
import com.ruoyi.cms.domain.Pv;
import com.ruoyi.cms.service.IPvService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * PVController
 * 
 * @author wujiyue
 * @date 2019-11-29
 */
@Controller
@RequestMapping("/cms/pv")
public class PvController extends BaseController
{
    private String prefix = "cms/pv";

    @Autowired
    private IPvService pvService;

    @RequiresPermissions("cms:pv:view")
    @GetMapping()
    public String pv()
    {
        return prefix + "/pv";
    }

    /**
     * 查询PV列表
     */
    @RequiresPermissions("cms:pv:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Pv pv)
    {
        startPage();
        List<Pv> list = pvService.selectPvList(pv);
        return getDataTable(list);
    }

    /**
     * 新增PV
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存PV
     */
    @RequiresPermissions("cms:pv:add")
    @Log(title = "PV", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Pv pv)
    {
        return toAjax(pvService.insertPv(pv));
    }

    /**
     * 修改PV
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Pv pv = pvService.selectPvById(id);
        mmap.put("pv", pv);
        return prefix + "/edit";
    }

    /**
     * 修改保存PV
     */
    @RequiresPermissions("cms:pv:edit")
    @Log(title = "PV", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Pv pv)
    {
        return toAjax(pvService.updatePv(pv));
    }

    /**
     * 删除PV
     */
    @RequiresPermissions("cms:pv:remove")
    @Log(title = "PV", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(pvService.deletePvByIds(ids));
    }
}
