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
import com.ruoyi.cms.domain.MaterialGroup;
import com.ruoyi.cms.service.IMaterialGroupService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.domain.Ztree;

/**
 * 素材分组Controller
 * 
 * @author wujiyue
 * @date 2019-11-07
 */
@Controller
@RequestMapping("/cms/materialGroup")
public class MaterialGroupController extends BaseController
{
    private String prefix = "cms/materialGroup";

    @Autowired
    private IMaterialGroupService materialGroupService;

    @RequiresPermissions("cms:materialGroup:view")
    @GetMapping()
    public String materialGroup()
    {
        return prefix + "/materialGroup";
    }

    /**
     * 查询素材分组树列表
     */
    @RequiresPermissions("cms:materialGroup:list")
    @PostMapping("/list")
    @ResponseBody
    public List<MaterialGroup> list(MaterialGroup materialGroup)
    {
        List<MaterialGroup> list = materialGroupService.selectMaterialGroupList(materialGroup);
        return list;
    }

    /**
     * 导出素材分组列表
     */
    @RequiresPermissions("cms:materialGroup:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MaterialGroup materialGroup)
    {
        List<MaterialGroup> list = materialGroupService.selectMaterialGroupList(materialGroup);
        ExcelUtil<MaterialGroup> util = new ExcelUtil<MaterialGroup>(MaterialGroup.class);
        return util.exportExcel(list, "materialGroup");
    }

    /**
     * 新增素材分组
     */
    @GetMapping(value = { "/add/{groupId}", "/add/" })
    public String add(@PathVariable(value = "groupId", required = false) Long groupId, ModelMap mmap)
    {
        if (StringUtils.isNotNull(groupId))
        {
            mmap.put("materialGroup", materialGroupService.selectMaterialGroupById(groupId));
        }
        return prefix + "/add";
    }

    /**
     * 新增保存素材分组
     */
    @RequiresPermissions("cms:materialGroup:add")
    @Log(title = "素材分组", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(MaterialGroup materialGroup)
    {
        return toAjax(materialGroupService.insertMaterialGroup(materialGroup));
    }

    /**
     * 修改素材分组
     */
    @GetMapping("/edit/{groupId}")
    public String edit(@PathVariable("groupId") Long groupId, ModelMap mmap)
    {
        MaterialGroup materialGroup = materialGroupService.selectMaterialGroupById(groupId);
        mmap.put("materialGroup", materialGroup);
        return prefix + "/edit";
    }

    /**
     * 修改保存素材分组
     */
    @RequiresPermissions("cms:materialGroup:edit")
    @Log(title = "素材分组", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MaterialGroup materialGroup)
    {
        return toAjax(materialGroupService.updateMaterialGroup(materialGroup));
    }

    /**
     * 删除
     */
    @RequiresPermissions("cms:materialGroup:remove")
    @Log(title = "素材分组", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{groupId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("groupId") Long groupId)
    {
        return toAjax(materialGroupService.deleteMaterialGroupById(groupId));
    }

    /**
     * 选择素材分组树
     */
    @GetMapping(value = { "/selectMaterialGroupTree/{groupId}", "/selectMaterialGroupTree/" })
    public String selectMaterialGroupTree(@PathVariable(value = "groupId", required = false) Long groupId, ModelMap mmap)
    {
        if (StringUtils.isNotNull(groupId))
        {
            mmap.put("materialGroup", materialGroupService.selectMaterialGroupById(groupId));
        }
        return prefix + "/tree";
    }

    /**
     * 加载素材分组树列表
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = materialGroupService.selectMaterialGroupTree();
        return ztrees;
    }
}
