package com.ruoyi.system.service;


import com.ruoyi.system.domain.ImportExcelResult;
import com.ruoyi.system.domain.SysUser;

import java.util.List;

/**
 * 1.校验数据的合法性
 * 2.初始化默认值
 * 3.保存数据库
 */
public interface IExcelImportService {

    /**
     * 导入保存
     * @param list
     * @param importType
     * @param currentUser
     * @return 返回每行数据的导入结果
     */
    public ImportExcelResult saveData(List<Object> list, String importType, SysUser currentUser);
}
