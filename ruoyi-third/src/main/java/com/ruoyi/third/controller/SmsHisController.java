package com.ruoyi.third.controller;

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
import com.ruoyi.third.domain.SmsHis;
import com.ruoyi.third.service.ISmsHisService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 短信发送历史Controller
 * 
 * @author wujiyue
 * @date 2019-10-11
 */
@Controller
@RequestMapping("/third/smsHis")
public class SmsHisController extends BaseController
{
    private String prefix = "third/smsHis";

    @Autowired
    private ISmsHisService smsHisService;

    @RequiresPermissions("third:smsHis:view")
    @GetMapping()
    public String smsHis()
    {
        return prefix + "/smsHis";
    }

    /**
     * 查询短信发送历史列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SmsHis smsHis)
    {
        startPage();
        List<SmsHis> list = smsHisService.selectSmsHisList(smsHis);
        return getDataTable(list);
    }

    /**
     * 导出短信发送历史列表
     */

    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SmsHis smsHis)
    {
        List<SmsHis> list = smsHisService.selectSmsHisList(smsHis);
        ExcelUtil<SmsHis> util = new ExcelUtil<SmsHis>(SmsHis.class);
        return util.exportExcel(list, "smsHis");
    }


    /**
     * 短信发送历史详情
     */
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap mmap)
    {
        SmsHis smsHis = smsHisService.selectSmsHisById(id);
        mmap.put("smsHis", smsHis);
        return prefix + "/detail";
    }


    /**
     * 删除短信发送历史
     */
    @Log(title = "短信发送历史", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(smsHisService.deleteSmsHisByIds(ids));
    }
}
