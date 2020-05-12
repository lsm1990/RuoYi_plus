package com.ruoyi.app.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ruoyi.app.common.interceptor.AuthenticationInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer
{
    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(authenticationInterceptor()).addPathPatterns("/**"); // 拦截所有请求
    }

    @Bean
    public AuthenticationInterceptor authenticationInterceptor()
    {
        return new AuthenticationInterceptor();
    }
}