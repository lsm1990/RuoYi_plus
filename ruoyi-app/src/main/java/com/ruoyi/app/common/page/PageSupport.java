package com.ruoyi.app.common.page;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.text.Convert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 表格数据处理
 */
public class PageSupport
{
    /**
     * 封装分页对象
     */
    public static PageDomain getPageDomain()
    {
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(Convert.toInt(getParameter(Constants.PAGE_NUM)));
        pageDomain.setPageSize(Convert.toInt(getParameter(Constants.PAGE_SIZE)));
        pageDomain.setOrderByColumn(getParameter(Constants.ORDER_BY_COLUMN));
        pageDomain.setIsAsc(getParameter(Constants.IS_ASC));
        return pageDomain;
    }

    public static PageDomain buildPageRequest()
    {
        return getPageDomain();
    }

    public static String getParameter(String name)
    {
        return getRequest().getParameter(name);
    }

    /**
     * 获取request
     */
    public static HttpServletRequest getRequest()
    {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
}
