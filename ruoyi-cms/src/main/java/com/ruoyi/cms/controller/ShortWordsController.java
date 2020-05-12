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
import com.ruoyi.cms.domain.ShortWords;
import com.ruoyi.cms.service.IShortWordsService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 励志短语Controller
 * 
 * @author wujiyue
 * @date 2019-11-22
 */
@Controller
@RequestMapping("/cms/shortWords")
public class ShortWordsController extends BaseController
{
    private String prefix = "cms/shortWords";

    @Autowired
    private IShortWordsService shortWordsService;

    @RequiresPermissions("cms:shortWords:view")
    @GetMapping()
    public String shortWords()
    {
        return prefix + "/shortWords";
    }

    /**
     * 查询励志短语列表
     */
    @RequiresPermissions("cms:shortWords:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ShortWords shortWords)
    {
        startPage();
        List<ShortWords> list = shortWordsService.selectShortWordsList(shortWords);
        return getDataTable(list);
    }

    /**
     * 导出励志短语列表
     */
    @RequiresPermissions("cms:shortWords:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ShortWords shortWords)
    {
        List<ShortWords> list = shortWordsService.selectShortWordsList(shortWords);
        ExcelUtil<ShortWords> util = new ExcelUtil<ShortWords>(ShortWords.class);
        return util.exportExcel(list, "shortWords");
    }

    /**
     * 新增励志短语
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存励志短语
     */
    @RequiresPermissions("cms:shortWords:add")
    @Log(title = "励志短语", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ShortWords shortWords)
    {
        return toAjax(shortWordsService.insertShortWords(shortWords));
    }

    /**
     * 修改励志短语
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ShortWords shortWords = shortWordsService.selectShortWordsById(id);
        mmap.put("shortWords", shortWords);
        return prefix + "/edit";
    }

    /**
     * 修改保存励志短语
     */
    @RequiresPermissions("cms:shortWords:edit")
    @Log(title = "励志短语", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ShortWords shortWords)
    {
        return toAjax(shortWordsService.updateShortWords(shortWords));
    }

    /**
     * 删除励志短语
     */
    @RequiresPermissions("cms:shortWords:remove")
    @Log(title = "励志短语", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(shortWordsService.deleteShortWordsByIds(ids));
    }
}
