package com.ruoyi.app.common.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.app.common.interceptor.AuthenticationInterceptor;
import com.ruoyi.app.common.page.PageSupport;
import com.ruoyi.app.common.page.ResultData;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.utils.StringUtils;

import java.util.List;

/**
 * web层通用数据处理
 */
public class BaseAppController
{
    /**
     * 设置请求分页数据
     */
    protected void startPage()
    {
        PageDomain pageDomain = PageSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize))
        {
            String orderBy = pageDomain.getOrderBy();
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }

    /**
     * 封装分页数据
     * @param list
     * @return
     * @author zmr
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    protected ResultData getDataPage(List<?> list)
    {
        ResultData rspData = new ResultData();
        rspData.setCode(0);
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * 响应返回结果
     * 
     * @param rows 影响行数
     * @return 操作结果
     */
    protected AjaxResult toAjax(int rows)
    {
        return rows > 0 ? success() : error();
    }

    /**
     * 返回成功
     */
    public AjaxResult success()
    {
        return AjaxResult.success();
    }

    /**
     * 返回失败消息
     */
    public AjaxResult error()
    {
        return AjaxResult.error();
    }

    /**
     * 返回成功消息
     */
    public AjaxResult success(String message)
    {
        return AjaxResult.success(message);
    }

    /**
     * 返回失败消息
     */
    public AjaxResult error(String message)
    {
        return AjaxResult.error(message);
    }



    /**
     * 页面跳转
     */
    public String redirect(String url)
    {
        return StringUtils.format("redirect:{}", url);
    }

    public static long getCurrentUserId()
    {
        Long userId = (Long) PageSupport.getRequest().getAttribute(AuthenticationInterceptor.USER_KEY);
        if (null == userId)
        {
            userId = 0l;
        }
        return userId;
    }
}
