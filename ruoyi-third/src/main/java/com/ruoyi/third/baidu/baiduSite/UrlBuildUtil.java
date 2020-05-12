package com.ruoyi.third.baidu.baiduSite;

import java.text.MessageFormat;

/**
 * Url构建工具类
 */
public class UrlBuildUtil {
    /**
     * 通过百度API 根据ip获取定位的接口
     */
    public static final String BAIDU_API_GET_LOCATION_BY_IP = "https://api.map.baidu.com/location/ip";

    /**
     * 百度提交链接时的Url
     */
    public static final String BAIDU_PUSH_URL = "http://data.zz.baidu.com/";

    private static final String GET_LOCATION_BY_IP = "{0}?ak={1}&coor=gcj02&ip={2}";
    private static final String BAIDU_PUSH_URL_PATTERN = "{0}{1}?site={2}&token={3}";

    /**
     * 根据ip获取定位信息的接口地址
     *
     * @param ip
     *         用户IP
     * @return
     */
    public static String getLocationByIp(String ip, String baiduApiAk) {
        return MessageFormat.format(GET_LOCATION_BY_IP, BAIDU_API_GET_LOCATION_BY_IP, baiduApiAk, ip);
    }

    /**
     * 提交链接到百度的接口地址
     *
     * @param pushType
     *         urls: 推送, update: 更新, del: 删除
     * @param site
     *         待提交的站点
     * @param baiduPushToken
     *         百度推送的token，百度站长平台获取
     * @return
     */
    public static String getBaiduPushUrl(String pushType, String site, String baiduPushToken) {
        return MessageFormat.format(BAIDU_PUSH_URL_PATTERN, BAIDU_PUSH_URL, pushType, site, baiduPushToken);
    }
}
