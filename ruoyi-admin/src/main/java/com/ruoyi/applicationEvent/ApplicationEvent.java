package com.ruoyi.applicationEvent;

import java.lang.annotation.*;

/**
 * 系统事件注解
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ApplicationEvent {
    ApplicationEventDefined[] value(); //事件名称
}
