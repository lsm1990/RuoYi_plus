package com.ruoyi.third.baidu.api;


import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.ehcache.util.EhCacheUtils;

import com.ruoyi.third.config.ThirdConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * 获取百度AI AccessToken
 */
public class AuthService {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    public static String KEY_AccessToken="BaiduAi.AccessToken";
    /**
     * 获得条用借口凭证 access_token
     */
    public static String getAccessToken(){
        //{"error_msg":"Access token expired","error_code":111}
        String access_token=(String) EhCacheUtils.getSysInfo(KEY_AccessToken);
        if(StringUtils.isEmpty(access_token)){
            String AK= ThirdConfig.getBaiduAiAk();
            String SK= ThirdConfig.getBaiduAiSk();
            access_token=AuthService.getAuth(AK,SK);
            EhCacheUtils.putSysInfo(KEY_AccessToken,access_token);
        }
        return access_token;
    }

    /**
     * 刷新AccessToken
     */
    public void refreshAccessToken(){
        String AK= ThirdConfig.getBaiduAiAk();
        String SK= ThirdConfig.getBaiduAiSk();
        String access_token=AuthService.getAuth(AK,SK);
        logger.info("refreshAccessToken=>"+access_token);
        if(StringUtils.isNotEmpty(access_token)){
            EhCacheUtils.putSysInfo(KEY_AccessToken,access_token);
            logger.info("refreshAccessToken=>"+access_token);
        }
    }
    /**
     * 获取权限token
     * @return 返回示例：
     * {
     * "access_token": "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567",
     * "expires_in": 2592000
     * }
     */
    //public static String getAuth() {
        // 官网获取的 API Key 更新为你注册的
        //String clientId = "百度云应用的AK";
        // 官网获取的 Secret Key 更新为你注册的
        //String clientSecret = "百度云应用的SK";
        //return getAuth(clientId, clientSecret);
        //this.ak
       // return getAuth(ak,sk);
   // }

    /**
     * 获取API访问token
     * 该token有一定的有效期，需要自行管理，当失效时需重新获取.
     * @param ak - 百度云官网获取的 API Key
     * @param sk - 百度云官网获取的 Securet Key
     * @return assess_token 示例：
     * "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567"
     */
    private static String getAuth(String ak, String sk) {
        // 获取token地址
        String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
        String getAccessTokenUrl = authHost
                // 1. grant_type为固定参数
                + "grant_type=client_credentials"
                // 2. 官网获取的 API Key
                + "&client_id=" + ak
                // 3. 官网获取的 Secret Key
                + "&client_secret=" + sk;
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            /*for (String key : map.keySet()) {
                System.err.println(key + "--->" + map.get(key));
            }*/
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }

            //System.err.println("result:" + result);
            JSONObject jsonObject=JSONObject.parseObject(result);
            String access_token = jsonObject.getString("access_token");
            return access_token;
        } catch (Exception e) {
            System.err.printf("获取token失败！");
            e.printStackTrace(System.err);
        }
        return null;
    }

    public static  void main(String[] args){
        //String res=getAuth("ID5y3zROMLcwXGz9c6b7CN5X","9uYkONYPw3om45rGzDbCRaHRzjyQuFGf");
        String res=getAuth("4rVbZSnDypW2QfwKIuxdXPPf","K8z8mrpUoyUCDHscfIgQSOz6KzL79Gzc");

        System.out.println(res);
    }
}