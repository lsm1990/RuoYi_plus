package com.ruoyi.cms.service.impl;

import com.ruoyi.cms.mapper.WebSiteMapper;
import com.ruoyi.cms.service.IWebSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class WebSiteService implements IWebSiteService {
    @Autowired
    private WebSiteMapper webSiteMapper;
    @Override
    public Map getSiteInfo() {
        return webSiteMapper.getSiteInfo();
    }
}
