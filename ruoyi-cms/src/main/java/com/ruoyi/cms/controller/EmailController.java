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
import com.ruoyi.cms.domain.Email;
import com.ruoyi.cms.service.IEmailService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * 邮件Controller
 * 
 * @author wujiyue
 * @date 2019-11-04
 */
@Controller
@RequestMapping("/cms/email")
public class EmailController extends BaseController
{
    private String prefix = "cms/email";

    @Autowired
    private IEmailService emailService;

    @RequiresPermissions("cms:email:view")
    @GetMapping()
    public String email()
    {
        return prefix + "/email";
    }

    /**
     * 查询邮件列表
     */
    @RequiresPermissions("cms:email:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Email email)
    {
        startPage();
        List<Email> list = emailService.selectEmailList(email);
        return getDataTable(list);
    }

    /**
     * 导出邮件列表
     */
    @RequiresPermissions("cms:email:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Email email)
    {
        List<Email> list = emailService.selectEmailList(email);
        ExcelUtil<Email> util = new ExcelUtil<Email>(Email.class);
        return util.exportExcel(list, "email");
    }

    /**
     * 新增邮件
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存邮件
     */
    @RequiresPermissions("cms:email:add")
    //@Log(title = "邮件", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Email email, HttpServletRequest request)
    {
        if(email.getContent()==null){
            String content=request.getParameter("content");
            email.setContent(content);
        }
        return toAjax(emailService.insertEmail(email));
    }

    /**
     * 修改邮件
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        Email email = emailService.selectEmailById(id);
        mmap.put("email", email);
        return prefix + "/edit";
    }

    /**
     * 修改保存邮件
     */
    @RequiresPermissions("cms:email:edit")
    //@Log(title = "邮件", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Email email, HttpServletRequest request)
    {
        if(email.getContent()==null){
            String content=request.getParameter("content");
            email.setContent(content);
        }
        return toAjax(emailService.updateEmail(email));
    }

    /**
     * 删除邮件
     */
    @RequiresPermissions("cms:email:remove")
    @Log(title = "邮件", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(emailService.deleteEmailByIds(ids));
    }

    @RequiresPermissions("cms:email:sendEmail")
    @Log(title = "发送邮件", businessType = BusinessType.OTHER)
    @PostMapping( "/sendEmail")
    @ResponseBody
    public AjaxResult sendEmail(String ids)
    {
        emailService.sendEmail(ids);
        return success();
    }
}
