package com.ruoyi.cms.controller;

import java.util.List;

import com.ruoyi.cms.domain.AdMaterial;
import com.ruoyi.cms.domain.AlbumMaterial;
import com.ruoyi.cms.domain.Material;
import com.ruoyi.common.utils.StringUtils;
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
import com.ruoyi.cms.domain.Ad;
import com.ruoyi.cms.service.IAdService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 广告位Controller
 * 
 * @author wujiyue
 * @date 2019-11-16
 */
@Controller
@RequestMapping("/cms/ad")
public class AdController extends BaseController
{
    private String prefix = "cms/ad";

    @Autowired
    private IAdService adService;

    @RequiresPermissions("cms:ad:view")
    @GetMapping()
    public String ad()
    {
        return prefix + "/ad";
    }

    /**
     * 查询广告位列表
     */
    @RequiresPermissions("cms:ad:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Ad ad)
    {
        startPage();
        List<Ad> list = adService.selectAdList(ad);
        return getDataTable(list);
    }

    /**
     * 导出广告位列表
     */
    @RequiresPermissions("cms:ad:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Ad ad)
    {
        List<Ad> list = adService.selectAdList(ad);
        ExcelUtil<Ad> util = new ExcelUtil<Ad>(Ad.class);
        return util.exportExcel(list, "ad");
    }

    /**
     * 新增广告位
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存广告位
     */
    @RequiresPermissions("cms:ad:add")
    @Log(title = "广告位", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Ad ad)
    {
        return toAjax(adService.insertAd(ad));
    }

    /**
     * 修改广告位
     */
    @GetMapping("/edit/{adId}")
    public String edit(@PathVariable("adId") Long adId, ModelMap mmap)
    {
        Ad ad = adService.selectAdById(adId);
        mmap.put("ad", ad);
        return prefix + "/edit";
    }

    /**
     * 修改保存广告位
     */
    @RequiresPermissions("cms:ad:edit")
    @Log(title = "广告位", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Ad ad)
    {
        return toAjax(adService.updateAd(ad));
    }

    /**
     * 删除广告位
     */
    @RequiresPermissions("cms:ad:remove")
    @Log(title = "广告位", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(adService.deleteAdByIds(ids));
    }

    /*****************************************分割线****************************************************/


    /**
     * 跳转配置广告页面
     */
    @GetMapping("/adMaterial/{adId}")
    public String adMaterial(@PathVariable("adId") String adId, ModelMap mmap)
    {
        mmap.put("adId", adId);
        return prefix + "/adMaterial";
    }

    /**
     * 查询广告位绑定的素材列表
     */
    @PostMapping("/adMaterialList")
    @ResponseBody
    public TableDataInfo adMaterialList(AdMaterial adMaterial)
    {
        List<AdMaterial> list = adService.selectAdMaterialList(adMaterial);
        return getDataTable(list);
    }

    /**
     * 跳转新增广告素材页面
     */
    @GetMapping("/addAdMaterial/{adId}")
    public String addAdMaterial(@PathVariable("adId") String adId, ModelMap mmap)
    {
        mmap.put("adId", adId);
        return prefix + "/addAdMaterial";
    }
    /**
     * 新增保存广告位
     */

    @PostMapping("/addAdMaterialSave")
    @ResponseBody
    public AjaxResult addAdMaterialSave(AdMaterial adMaterial)
    {
        return toAjax(adService.insertAdMaterial(adMaterial));
    }
    /**
     * 跳转编辑广告素材页面
     */
    @GetMapping("/editAdMaterial/{id}")
    public String editAdMaterial(@PathVariable("id") Long id, ModelMap mmap)
    {
        AdMaterial adMaterial = adService.selectAdMaterialById(id);

        mmap.put("adMaterial", adMaterial);
        return prefix + "/editAdMaterial";
    }

    /**
     * 修改保存广告位素材
     */
    @PostMapping("/editAdMaterialSave")
    @ResponseBody
    public AjaxResult editAdMaterialSave(AdMaterial adMaterial)
    {
        return toAjax(adService.updateAdMaterial(adMaterial));
    }

    /**
     * 删除广告位素材
     */
    @PostMapping( "/removeAdMaterial")
    @ResponseBody
    public AjaxResult removeAdMaterial(String ids)
    {
        return toAjax(adService.deleteAdMaterialByIds(ids));
    }


    /**
     * 打开广告素材选择界面
     * @return
     */
    @GetMapping("/selectAdMaterial/{adId}")
    public String selectAdMaterial(@PathVariable("adId") String adId, ModelMap mmap)
    {
        mmap.put("adId", adId);
        return prefix + "/selectAdMaterial";
    }

    /**
     * 查询未配置的素材
     * @param adMaterial
     * @return
     */
    @PostMapping("/unMaterialList")
    @ResponseBody
    public TableDataInfo unMaterialList(AdMaterial adMaterial)
    {
        startPage();
        List<AdMaterial> list = adService.selectAdUnMaterialList(adMaterial);
        return getDataTable(list);
    }


}
