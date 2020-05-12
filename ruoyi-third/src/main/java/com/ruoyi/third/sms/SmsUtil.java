package com.ruoyi.third.sms;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.ICallBack;
import com.ruoyi.common.utils.StringUtils;

import com.ruoyi.third.config.ThirdConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 发送短信工具类
 * @author wujiyue
 */
public class SmsUtil {
    protected static Logger logger = LoggerFactory.getLogger(SmsUtil.class);

    private static String leYunAccount= ThirdConfig.getSmsLeYunAccount();

    private static String leYunPassword= ThirdConfig.getSmsLeYunPassword();

    /**
     * 乐云短信
     * @param phone     发送的号码
     * @param content   发送短信内容
     * @param prefix    内容前缀该字符串会被“【】”包裹并添加到content最前面
     * @return
     */
    public static AjaxResult sendLeYunSms(String phone, String prefix, String content) {
        try {
            if(StringUtils.isNotEmpty(prefix)){
                prefix="【"+prefix+"】";
                content=prefix+content;
            }
            content = java.net.URLEncoder.encode(content, "UTF-8");// 用UTF-8编码执行URLEncode
            String _url = "http://www.lehuo520.cn/a/sms/api/send"; //接口地址
            String _param = "username="+leYunAccount+"&password="+leYunPassword + "&phone=" + phone+ "&content=" + content+ "&type=1";
            URL url = null;
            HttpURLConnection urlConn = null;
            url = new URL(_url);
            urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setRequestMethod("POST");
            urlConn.setDoOutput(true);           OutputStream out = urlConn.getOutputStream();
            out.write(_param.getBytes("UTF-8"));
            out.flush();
            out.close();
            BufferedReader rd = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "UTF-8"));
            StringBuffer sb = new StringBuffer();
            int ch;
            while ((ch = rd.read()) > -1) {
                sb.append((char) ch);
            }
            System.out.println(sb);
            //{"result":"-1","describing":"帐号不存在，请检查用户名或者密码是否正确","sms":[]}
            rd.close();
            JSONObject jsonObject= JSON.parseObject(sb.toString());
            int code= jsonObject.getIntValue("result");
            String msg=jsonObject.getString("describing");
            if(code==0){
                return AjaxResult.success(msg);
            }else{
                return AjaxResult.error(msg);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return AjaxResult.error(ex.getMessage());
        }

    }
    /**
     * 乐云短信
     * @param phone     发送的号码
     * @param content   发送短信内容
     * @param callBack  回调函数，传入该接口的一个实现类并定义成功和失败后执行的方法
     * @return
     */
    public static AjaxResult sendLeYunSms(String phone, String prefix, String content, ICallBack callBack){
        AjaxResult res=sendLeYunSms(phone,prefix, content);
        if(res.isSuccess()){
            callBack.onSuccess();
        }else{
            callBack.onFail();
        }
        return res;
    }


}
