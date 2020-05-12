package com.ruoyi.cms.mapper;

import com.ruoyi.cms.domain.Template;

import java.util.List;
import java.util.Map;

/**
 * 站点相关
 * 
 * @author wujiyue
 * @date 2019-11-17
 */
public interface WebSiteMapper
{
    /**
     * 获得站点相关数据
     * @return
     */
    public Map getSiteInfo();
}
