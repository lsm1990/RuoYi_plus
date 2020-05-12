package com.zebra.api.consumer.config;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.zebra.api.consumer.interceptor.MyInterceptor;

@Configuration
public class ZebraConfig implements WebMvcConfigurer {

	/**
	 * 定义拦截器类
	 *
	 * @return
	 */
	@Bean
	public MyInterceptor myInterceptor() {
		return new MyInterceptor();
	}

	/**
	 * 拦截器配置
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(myInterceptor());
	}

	/**
	 * 编解码配置
	 *
	 * @return
	 */
	@Bean
	public HttpMessageConverters MyHttpMessageConverters() {
		HttpMessageConverter fastConverter = new HttpMessageConverter();
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteDateUseDateFormat);
		fastConverter.setFastJsonConfig(fastJsonConfig);
		org.springframework.http.converter.HttpMessageConverter<Object> converter = fastConverter;
		return new HttpMessageConverters(converter);
	}
}
