package com.ruoyi.applicationEvent;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.EventLog;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.IEventLogService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * 事件管理器
 * Created by zengchao on 2017-03-09.
 */
public class ApplicationEventManager {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Map<ApplicationEventDefined, List<IApplicationEvent>> eventMap = new HashMap<>();//按照系事件分组

    List<IApplicationEvent> applicationEventList;//所有实现系统事件类

    IEventLogService eventLogService;

    public void setApplicationEventList(List<IApplicationEvent> applicationEventList) {
        this.applicationEventList = applicationEventList;
    }

    public void setEventLogService(IEventLogService eventLogService) {
        this.eventLogService = eventLogService;
    }

    /**
     * 绑定事件
     *
     * @param applicationEventDefineds 系统自定义事件数组
     * @param applicationEvent     事件处理程序
     */
    public synchronized void bind(ApplicationEventDefined[] applicationEventDefineds, IApplicationEvent applicationEvent) {
        for(ApplicationEventDefined applicationEventDefined:applicationEventDefineds){
            List<IApplicationEvent> eventList = eventMap.get(applicationEventDefined);
            if (eventList == null) {
                eventList = new ArrayList<>();
            }
            eventList.add(applicationEvent);
            eventMap.put(applicationEventDefined, eventList);

            logger.debug("Bind Event : " + applicationEventDefined.name());
        }

    }

    /**
     * 解绑事件
     *
     * @param applicationEventDefined 事件名称
     * @param applicationEvent     事件处理程序
     */
    public synchronized void unbind(ApplicationEventDefined applicationEventDefined, IApplicationEvent applicationEvent) {
        List<IApplicationEvent> eventList = eventMap.get(applicationEventDefined);
        if (eventList == null) {
            return;
        }
        eventList.remove(applicationEvent);
        logger.debug("UnBind Event : " + applicationEventDefined.name());
    }

    /**
     * 触发事件
     *
     * @param applicationEventDefined 事件名称
     * @param source    来源
     * @param params    参数
     */
    public synchronized void trigger(ApplicationEventDefined applicationEventDefined, Object source, Object params) {
        List<IApplicationEvent> eventList = eventMap.get(applicationEventDefined);
        if (eventList == null) {
            return;
        }
        for (IApplicationEvent event : eventList) {
            try {
                event.onTrigger(source, params);
                saveLog(applicationEventDefined,source,params);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void saveLog(ApplicationEventDefined applicationEventDefined, Object source, Object params){
            String clsName="";
            if(source instanceof String){
                clsName=source.toString();
            }else{
                clsName=source.getClass().getTypeName();
            }
            JSONObject jsonObject=JSONObject.parseObject(JSON.toJSONString(params));
            String orgid=(String)jsonObject.get("orgid");
            EventLog log= new EventLog();
            SysUser user= ShiroUtils.getSysUser();
            log.setUserId(user.getUserId().toString());
            log.setUserName(user.getUserName());
            log.setEventCode(applicationEventDefined.getValue());
            log.setEventName(applicationEventDefined.getDescription());
            log.setCreateTime(new Date());
            log.setSource(clsName);
            log.setDatas(JSON.toJSONString(params));
            eventLogService.insertEventLog(log);
    }
    /**
     * 初始化
     * 扫描系统中注解形式的事件
     */
    @PostConstruct
    public void init() {
        if(CollectionUtils.isNotEmpty(applicationEventList)){
            for(IApplicationEvent event:applicationEventList){
                 Class cls =   event.getClass();
                Object instance = null;
                if(!cls.getName().contains("proxy")){
                    instance= SpringUtils.getBean(cls);
                    if (instance instanceof IApplicationEvent) {
                        ApplicationEvent eventAnno =(ApplicationEvent) cls.getAnnotation(ApplicationEvent.class);
                        this.bind(eventAnno.value(), (IApplicationEvent) instance);
                    }
                }
            }
            logger.info("系统事件总计注册数量：" + this.eventMap.size());
            //遍历自定义系统内置事件列出各个事件注册数量
            ApplicationEventDefined[] applicationEventDefineds= ApplicationEventDefined.values();
            for(ApplicationEventDefined applicationEventDefined:applicationEventDefineds){
                List<IApplicationEvent> eventList = eventMap.get(applicationEventDefined);
                if(CollectionUtils.isNotEmpty(eventList)){
                    logger.info("系统事件"+applicationEventDefined.getValue()+"["+applicationEventDefined.getDescription()+"]注册数量：" + eventList.size());
                }else{
                    logger.info("系统事件"+applicationEventDefined.getValue()+"["+applicationEventDefined.getDescription()+"]注册数量：0");
                }
            }

        }
        /*Set<Class<?>> classes = ClassKit.getClassesByAnnotation("com.jrelax", ApplicationEvent.class);
        for (Class<?> cls : classes) {
            Object instance = null;
            Map<String, ?> beanMap = ApplicationContextHelper.getInstance().getBeansOfType(cls);
            if (beanMap.size() == 0) {
                instance = ApplicationContextHelper.getInstance().createBean(cls);
            } else {
                for (Map.Entry<String, ?> map : beanMap.entrySet()) {
                    instance = map.getValue();

                    if (instance != null) break;
                }
            }
            if (instance instanceof IApplicationEvent) {
                ApplicationEvent event = cls.getAnnotation(ApplicationEvent.class);

                this.bind(event.value(), (IApplicationEvent) instance);
            }
        }

        logger.info("系统事件注册数量：" + this.eventMap.size());*/
    }
}
