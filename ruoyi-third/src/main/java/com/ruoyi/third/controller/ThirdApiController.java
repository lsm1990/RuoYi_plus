package com.ruoyi.third.controller;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.third.baidu.baiduSite.BaiduPushTypeEnum;
import com.ruoyi.third.baidu.baiduSite.BaiduPushUtil;
import com.ruoyi.third.baidu.baiduSite.UrlBuildUtil;
import com.ruoyi.third.config.ThirdConfig;
import com.ruoyi.third.service.impl.LeYunSmsCallBackImpl;
import com.ruoyi.third.sms.SmsUtil;
import com.ruoyi.third.tencent.TencentIpUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 第三方APIController
 * 
 * @author wujiyue
 * @date 2019-10-11
 */
@Controller
@RequestMapping("/third/api")
public class ThirdApiController extends BaseController
{
    private String prefix = "third/api";

    @RequiresPermissions("third:api:view")
    @GetMapping()
    public String smsHis()
    {
        return prefix + "/thirdApi";
    }


    /**
     * 发送短信
     */
    @RequiresPermissions("third:api:sendSms")
    @PostMapping("/sendSms")
    @ResponseBody
    public AjaxResult sendSms(String phone,String prefix,String content)
    {
        LeYunSmsCallBackImpl leYunSmsCallBack= SpringUtils.getBean("leYunSmsCallBack");
        Map map=new HashMap<String,Object>();
        map.put("phone",phone);
        map.put("prefix",prefix);
        map.put("content",content);
        leYunSmsCallBack.setParams(map);
        AjaxResult result= SmsUtil.sendLeYunSms(phone,prefix,content,leYunSmsCallBack);
        return result;
    }
    /**
     * 查询IP
     */
    @RequiresPermissions("third:api:queryIp")
    @PostMapping("/queryIp")
    @ResponseBody
    public AjaxResult queryIp(String ip)
    {
        AjaxResult res=TencentIpUtil.queryIpLocation(ip);
        return res;
    }

    /**
     * 查询IP
     */
    @RequiresPermissions("third:api:baiduPush")
    @PostMapping("/baiduPush/{type}")
    @ResponseBody
    public AjaxResult baiduPush(@PathVariable("type") BaiduPushTypeEnum type,String url)
    {
        String str = UrlBuildUtil.getBaiduPushUrl(type.toString(), ThirdConfig.getBaiduPushDomain(), ThirdConfig.getBaiduPushToken());
        /**
         * success	       	int	    成功推送的url条数
         * remain	       	int	    当天剩余的可推送url条数
         * not_same_site	array	由于不是本站url而未处理的url列表
         * not_valid	   	array	不合法的url列表
         */
        // {"remain":4999997,"success":1,"not_same_site":[],"not_valid":[]}
        /**
         * error	是	int	      错误码，与状态码相同
         * message	是	string	  错误描述
         */
        //{error":401,"message":"token is not valid"}
        String result = BaiduPushUtil.doPush(str, url);

        JSONObject resultJson = JSONObject.parseObject(result);

        /*if (resultJson.containsKey("error")) {
            return AjaxResult.error(resultJson.getString("message"));
        }*/
        return AjaxResult.success(resultJson);
    }

}
