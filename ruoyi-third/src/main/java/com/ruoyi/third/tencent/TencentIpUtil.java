package com.ruoyi.third.tencent;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.third.config.ThirdConfig;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 *腾讯ip位置查询服务
 */
public class TencentIpUtil {

    private static final String MY_IP_KEY= ThirdConfig.getTencentIpKey();
    private static String URL_IP="https://apis.map.qq.com/ws/location/v1/ip?ip=IP&key=KEY";
    public static AjaxResult queryIpLocation(String ip){
        String address="";
        String url=URL_IP.replace("IP",ip).replace("KEY",MY_IP_KEY);
        String data=  sendRequest(url, RequestType.GET);
        JSONObject jsonObject= JSONObject.parseObject(data);
        String status=String.valueOf(jsonObject.get("status"));
        String msg=String.valueOf(jsonObject.get("message"));
        if("0".equals(status)){
            //查询成功
            JSONObject ad_infoObj=jsonObject.getJSONObject("result").getJSONObject("ad_info");
            String nation=String.valueOf(ad_infoObj.get("nation"));//国家
            String province=String.valueOf(ad_infoObj.get("province"));//省
            String city=String.valueOf(ad_infoObj.get("city"));//市
            String district=String.valueOf(ad_infoObj.get("district"));//区
            if("中国".equals(nation)){
                address+= (StringUtils.isNotEmpty(province)?province.replace("省",""):"")+(StringUtils.isNotEmpty(city)?"-"+city.replaceAll("市",""):"")+(StringUtils.isNotEmpty(district)?"-"+district:"");
            }else{
                address+= (StringUtils.isNotEmpty(nation)?nation:"")+(StringUtils.isNotEmpty(province)?"-"+province:"")+(StringUtils.isNotEmpty(city)?"-"+city:"")+(StringUtils.isNotEmpty(district)?"-"+district:"");
            }
            return AjaxResult.success("查询成功!",address);
        }else{
            //失败的情况
            return AjaxResult.error("查询失败!",msg);
        }

    }
    public static void main(String[] args){
       //String res= queryIpLocation("112.232.19.111");
       // System.out.println(res);
    }
    public enum RequestType{
        POST,
        GET
    }
    private static String sendRequest(String requestUrl,RequestType type){
        try {
            URL url = new URL(requestUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(type.name());
            // 设置通用的请求属性
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setUseCaches(false);
            connection.setDoOutput(true);
            connection.setDoInput(true);

            // 得到请求的输出流对象
            //DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            //out.writeBytes(params);
            //out.flush();
            //out.close();

            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> headers = connection.getHeaderFields();
            // 遍历所有的响应头字段
           // for (String key : headers.keySet()) {
                //System.out.println(key + "--->" + headers.get(key));
            //}
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = null;

            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

            String result = "";
            String getLine;
            while ((getLine = in.readLine()) != null) {
                result += getLine;
            }
            in.close();
            // System.out.println("result:" + result);
            return result;
        }catch (Exception ex){
            ex.printStackTrace();
            return "";
        }
    }
}
