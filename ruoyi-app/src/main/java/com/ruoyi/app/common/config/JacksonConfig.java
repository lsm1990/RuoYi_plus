package com.ruoyi.app.common.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 〈返回json空值去掉null和""〉 〈功能详细描述〉
 * 
 * @author gogym
 * @version 2017年10月13日
 * @see JacksonConfig
 * @since
 */
@Configuration
public class JacksonConfig
{
    @Bean
    @Primary
    @ConditionalOnMissingBean(ObjectMapper.class)
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder)
    {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        // 通过该方法对mapper对象进行设置，所有序列化的对象都将按改规则进行系列化
        // Include.Include.ALWAYS 默认
        // Include.NON_DEFAULT 属性为默认值不序列化
        // Include.NON_EMPTY 属性为 空（""） 或者为 NULL
        // 都不序列化，则返回的json是没有这个字段的。这样对移动端会更省流量
        // Include.NON_NULL 属性为NULL 不序列化,就是为null的字段不参加序列化
        // objectMapper.setSerializationInclusion(Include.NON_EMPTY);
        // 字段保留，将null值转为""
        objectMapper.setSerializationInclusion(Include.NON_EMPTY);
        // objectMapper.getSerializerProvider().setNullValueSerializer(new
        // JsonSerializer<Object>()
        // {
        // @Override
        // public void serialize(Object o, JsonGenerator jsonGenerator,
        // SerializerProvider serializerProvider)
        // throws IOException, JsonProcessingException
        // {
        // jsonGenerator.writeString("");
        // }
        // });
        return objectMapper;
    }
}
