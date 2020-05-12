package com.ruoyi.applicationEvent.config;


import com.ruoyi.applicationEvent.ApplicationEventAspect;
import com.ruoyi.applicationEvent.ApplicationEventManager;
import com.ruoyi.applicationEvent.IApplicationEvent;
import com.ruoyi.system.service.IEventLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 系统事件配置
 *
 * @author ruoyi
 */
@Configuration
public class ApplicationEventConfig
{

    @Autowired
    List<IApplicationEvent> applicationEventList;//所有实现系统事件类
    @Autowired
    IEventLogService eventLogService;//系统事件日志记录

    /**
     * 系统事件管理器
     * @return
     */
    @Bean("applicationEventManager")
    public ApplicationEventManager getApplicationEventManager(){
        ApplicationEventManager applicationEventManager =new ApplicationEventManager();
        applicationEventManager.setApplicationEventList(applicationEventList);
        applicationEventManager.setEventLogService(eventLogService);
        return applicationEventManager;
    }

    /**
     * 系统事件触发切面类
     */
    @Bean("applicationEventAspect")
    public ApplicationEventAspect getApplicationEventAspect(){
        ApplicationEventAspect applicationEventAspect=new ApplicationEventAspect();
        applicationEventAspect.setApplicationEventManager(getApplicationEventManager());
        return applicationEventAspect;
    }
}
