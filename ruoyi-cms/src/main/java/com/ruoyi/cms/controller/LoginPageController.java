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
import com.ruoyi.cms.domain.LoginPage;
import com.ruoyi.cms.service.ILoginPageService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 登录页面Controller
 * 
 * @author wujiyue
 * @date 2019-12-24
 */
@Controller
@RequestMapping("/cms/loginPage")
public class LoginPageController extends BaseController
{
    private String prefix = "cms/loginPage";

    @Autowired
    private ILoginPageService loginPageService;

    @RequiresPermissions("cms:loginPage:view")
    @GetMapping()
    public String loginPage()
    {
        return prefix + "/loginPage";
    }

    /**
     * 查询登录页面列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(LoginPage loginPage)
    {
        startPage();
        List<LoginPage> list = loginPageService.selectLoginPageList(loginPage);
        return getDataTable(list);
    }

    /**
     * 导出登录页面列表
     */
    @Log(title = "登录页面", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(LoginPage loginPage)
    {
        List<LoginPage> list = loginPageService.selectLoginPageList(loginPage);
        ExcelUtil<LoginPage> util = new ExcelUtil<LoginPage>(LoginPage.class);
        return util.exportExcel(list, "loginPage");
    }

    /**
     * 新增登录页面
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存登录页面
     */
    @Log(title = "登录页面", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(LoginPage loginPage)
    {
        return toAjax(loginPageService.insertLoginPage(loginPage));
    }

    /**
     * 修改登录页面
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        LoginPage loginPage = loginPageService.selectLoginPageById(id);
        mmap.put("loginPage", loginPage);
        return prefix + "/edit";
    }

    /**
     * 修改保存登录页面
     */
    @Log(title = "登录页面", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(LoginPage loginPage)
    {
        return toAjax(loginPageService.updateLoginPage(loginPage));
    }

    /**
     * 删除登录页面
     */
    @Log(title = "登录页面", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(loginPageService.deleteLoginPageByIds(ids));
    }
}
