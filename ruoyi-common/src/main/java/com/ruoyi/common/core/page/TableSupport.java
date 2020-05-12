package com.ruoyi.common.core.page;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.ServletUtils;

/**
 * 表格数据处理
 * 
 * @author ruoyi
 */
public class TableSupport
{
    /**
     * 封装分页对象
     */
    public static PageDomain getPageDomain()
    {
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(ServletUtils.getParameterToInt(Constants.PAGE_NUM));
        pageDomain.setPageSize(ServletUtils.getParameterToInt(Constants.PAGE_SIZE));
        pageDomain.setOrderByColumn(ServletUtils.getParameter(Constants.ORDER_BY_COLUMN));
        pageDomain.setIsAsc(ServletUtils.getParameter(Constants.IS_ASC));
        if(pageDomain.getPageNum()==null||pageDomain.getPageNum()==0){
            pageDomain.setPageNum(1);
        }
        if(pageDomain.getPageSize()==null||pageDomain.getPageSize()<=0){
            pageDomain.setPageSize(10);
        }
        return pageDomain;
    }

    public static PageDomain buildPageRequest()
    {
        return getPageDomain();
    }
}
