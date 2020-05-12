package com.ruoyi.cms.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * cms模块相关配置
 * 
 * @author wujiyue
 */
@Component
@PropertySource(value = { "classpath:cms.properties" })
public class CmsConfig
{

    public static String mailHost;

    public static String mailPort;
    
    /**
     *邮件发送服务
     */
    public static String  fromEmail;
    /**
     * 邮件发送服务
     */
    public static String fromEmailPwd;
    @Value("${cms.email.host}")
    public  void setMailHost(String mailHost) {
        CmsConfig.mailHost = mailHost;
    }
    @Value("${cms.email.port}")
    public  void setMailPort(String mailPort) {
        CmsConfig.mailPort = mailPort;
    }

    @Value("${cms.email.fromEmail}")
    public  void setFromEmail(String fromEmail) {
        CmsConfig.fromEmail = fromEmail;
    }
    @Value("${cms.email.fromEmailPwd}")
    public  void setFromEmailPwd(String fromEmailPwd) {
        CmsConfig.fromEmailPwd = fromEmailPwd;
    }

    public static String getMailHost() {
        return mailHost;
    }



    public static String getMailPort() {
        return mailPort;
    }



    public static String getFromEmail() {
        return fromEmail;
    }

    public static String getFromEmailPwd() {
        return fromEmailPwd;
    }


}
