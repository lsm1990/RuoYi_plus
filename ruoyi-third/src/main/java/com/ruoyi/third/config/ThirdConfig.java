package com.ruoyi.third.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 读取代码生成相关配置
 * 
 * @author wujiyue
 */
@Component
@PropertySource(value = { "classpath:third.properties" })
public class ThirdConfig
{
    
    /**
     *腾讯位置服务配置KEY
     */
    public static String  tencentIpKey;
    /**
     * 百度人工智能AK
     */
    public static String baiduAiAk;

    /**
     * 百度人工智能SK
     */
    public static String baiduAiSk;
    /**
     * 腾讯验证码服务appId
     */
    public static String  tencentCaptchaAppId;
    /**
     * 腾讯验证码服务appSecretKey
     */
    public static String  tencentCaptchaAppSecretKey;
    /**
     * 乐云短信服务帐号
     */
    public static String  smsLeYunAccount;
    /**
     * 乐云短信服务密码
     */
    public static String  smsLeYunPassword;

    /**
     * 百度推送cookie.自行登录百度站长平台后获取响应的cookie
     */
    public static String baiduPushCookie;
    /**
     * 百度推送使用的token
     */
    public static String baiduPushToken;
    public static String baiduPushDomain;


    @Value("${third.baidu.push.domain}")
    public  void setBaiduPushDomain(String baiduPushDomain) {
        ThirdConfig.baiduPushDomain = baiduPushDomain;
    }
    @Value("${third.baidu.push.token}")
    public  void setBaiduPushToken(String baiduPushToken) {
        ThirdConfig.baiduPushToken = baiduPushToken;
    }

    @Value("${third.baidu.push.cookie}")
    public  void setBaiduPushCookie(String baiduPushCookie) {
        ThirdConfig.baiduPushCookie = baiduPushCookie;
    }


    @Value("${third.tencent.ip_key}")
    public  void setTencentIpKey(String tencentIpKey) {
        ThirdConfig.tencentIpKey = tencentIpKey;
    }
    @Value("${third.baidu.ai.AK}")
    public  void setBaiduAiAk(String baiduAiAk) {
        ThirdConfig.baiduAiAk = baiduAiAk;
    }
    @Value("${third.baidu.ai.SK}")
    public  void setBaiduAiSk(String baiduAiSk) {
        ThirdConfig.baiduAiSk = baiduAiSk;
    }
    @Value("${third.tencent.tencentCaptcha.appId}")
    public  void setTencentCaptchaAppId(String tencentCaptchaAppId) {
        ThirdConfig.tencentCaptchaAppId = tencentCaptchaAppId;
    }
    @Value("${third.tencent.tencentCaptcha.appSecretKey}")
    public  void setTencentCaptchaAppSecretKey(String tencentCaptchaAppSecretKey) {
        ThirdConfig.tencentCaptchaAppSecretKey = tencentCaptchaAppSecretKey;
    }
    @Value("${third.sms.LeYun.account}")
    public  void setSmsLeYunAccount(String smsLeYunAccount) {
        ThirdConfig.smsLeYunAccount = smsLeYunAccount;
    }
    @Value("${third.sms.LeYun.password}")
    public  void setSmsLeYunPassword(String smsLeYunPassword) {
        ThirdConfig.smsLeYunPassword = smsLeYunPassword;
    }

    public static String getTencentIpKey() {
        return tencentIpKey;
    }

    public static String getBaiduAiAk() {
        return baiduAiAk;
    }

    public static String getBaiduAiSk() {
        return baiduAiSk;
    }

    public static String getTencentCaptchaAppId() {
        return tencentCaptchaAppId;
    }

    public static String getTencentCaptchaAppSecretKey() {
        return tencentCaptchaAppSecretKey;
    }

    public static String getSmsLeYunAccount() {
        return smsLeYunAccount;
    }

    public static String getSmsLeYunPassword() {
        return smsLeYunPassword;
    }

    public static String getBaiduPushCookie() {
        return baiduPushCookie;
    }

    public static String getBaiduPushToken() {
        return baiduPushToken;
    }
    public static String getBaiduPushDomain() {
        return baiduPushDomain;
    }
}
