package com.zebra.web.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zebra.common.config.ConfigServerApplication;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2的接口配置
 *
 * @author ruoyi
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Autowired
	private ConfigServerApplication configServerApplication;

	/**
	 * 创建API
	 */
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				// 用来创建该API的基本信息，展示在文档的页面中（自定义展示的信息）
				.apiInfo(apiInfo())
				// 设置哪些接口暴露给Swagger展示
				.select()
				// 扫描所有有注解的api，用这种方式更灵活
				.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
				// 扫描指定包中的swagger注解
				// .apis(RequestHandlerSelectors.basePackage("com.ruoyi.project.tool.swagger"))
				// 扫描所有 .apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
	}

	/**
	 * 添加摘要信息
	 */
	private ApiInfo apiInfo() {
		// 用ApiInfoBuilder进行定制
		return new ApiInfoBuilder()
				// 设置标题
				.title("标题：ruoyi-plus管理系统_API接口文档")
				// 描述
				.description("用途：用于提供APP、微端、H5等API接口服务")
				// 作者信息
				.contact(new Contact(configServerApplication.getName(), null, null))
				// 版本
				.version("版本号:" + configServerApplication.getVersion()).build();
	}
}
