package com.ruoyi.framework.config;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.DispatcherType;

import com.ruoyi.framework.shiro.web.filter.IPFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.xss.XssFilter;

/**
 * Filter配置
 *
 * @author ruoyi
 */
@Configuration
public class FilterConfig
{
    @Value("${xss.enabled}")
    private String enabled;

    @Value("${xss.excludes}")
    private String excludes;

    @Value("${xss.urlPatterns}")
    private String urlPatterns;

    @Value("${ip.enabled}")
    private String enabledIp;

    @Value("${ip.excludes}")
    private String excludesIP;

    @Value("${ip.urlPatterns}")
    private String urlPatternsIP;

    @Value("${ip.list}")
    private String ipList;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public FilterRegistrationBean xssFilterRegistration()
    {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new XssFilter());
        registration.addUrlPatterns(StringUtils.split(urlPatterns, ","));
        registration.setName("xssFilter");
        registration.setOrder(Integer.MAX_VALUE);
        Map<String, String> initParameters = new HashMap<String, String>();
        initParameters.put("excludes", excludes);
        initParameters.put("enabled", enabled);
        registration.setInitParameters(initParameters);
        return registration;
    }



    @Bean
    public FilterRegistrationBean ipRegistration()
    {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new IPFilter());
        registration.addUrlPatterns(StringUtils.split(urlPatternsIP, ","));
        registration.setName("ipFilter");
        registration.setOrder(1);
        Map<String, String> initParameters = new HashMap<String, String>();
        initParameters.put("excludes", excludesIP);
        initParameters.put("list", ipList);
        initParameters.put("enabled", enabledIp);
        registration.setInitParameters(initParameters);
        return registration;
    }


}
