package com.ruoyi.web.controller.system;


import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poiNew.ExcelUtilNew;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.IExcelImportService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户信息
 *
 * @author lws
 */
@Controller
@RequestMapping("/system/excelImport")
public class ExcelImportController extends BaseController {
    private String prefix = "system/excelImport";

    @Autowired
    private IExcelImportService excelImportService;

    @RequiresPermissions("system:excelImport:view")
    @GetMapping()
    public String user() {
        return prefix + "/excelImport";
    }

    private static Map<String,String> excelNameMap = new HashMap<String,String>();
    static {
        excelNameMap.put("1","用户导入");
        excelNameMap.put("2","部门导入");
    }
    @RequestMapping("/downloadExcelModel")
    @ResponseBody
    @Log(title = "Excel导入-模版下载", businessType = BusinessType.EXPORT)
    public AjaxResult downloadExcelModel(String importType){
        ExcelUtilNew excelUtil = excelUtilObj(importType);
        return excelUtil.exportExcelModel(excelNameMap.get(importType));
    }
    @PostMapping("/save")
    @ResponseBody
    @Log(title = "Excel导入-导入", businessType = BusinessType.IMPORT)
    public AjaxResult save(MultipartFile excelFile, String importType) {
        try {
            SysUser currentUser= ShiroUtils.getSysUser();
            ExcelUtilNew excelUtil = excelUtilObj(importType);
            List<Object> data = excelUtil.importExcel(excelFile.getInputStream());
            return AjaxResult.success(excelImportService.saveData(data,importType,currentUser));
        } catch (Exception e) {
            e.printStackTrace();
            return error("请选择正确的excel文件！");
        }
    }

    /**
     * 创建excel工具对象
     * @param type
     * @return
     */
    private static ExcelUtilNew excelUtilObj(String type){
        ExcelUtilNew excelUtil = null;
        switch (type){
            case "1":
                excelUtil= new ExcelUtilNew(SysUser.class);
                break;
            case "2":
                excelUtil= new ExcelUtilNew(SysDept.class);
                break;
            default:
                throw new RuntimeException("不支持的导入数据类型！");
        }
        return excelUtil;
    }
}