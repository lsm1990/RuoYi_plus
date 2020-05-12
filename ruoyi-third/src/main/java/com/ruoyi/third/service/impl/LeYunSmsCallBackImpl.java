package com.ruoyi.third.service.impl;


import com.google.common.collect.Maps;
import com.ruoyi.common.core.domain.ICallBack;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.third.domain.SmsHis;
import com.ruoyi.third.service.ISmsHisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author wujiyue
 */
@Service("leYunSmsCallBack")
@Scope("prototype")
public class LeYunSmsCallBackImpl implements ICallBack {

    @Autowired
    ISmsHisService smsHisService;
    Map params= Maps.newConcurrentMap();

    @Override
    public void onSuccess() {

        String returncode="1";
        params.put("returncode",returncode);

        String prefix=String.valueOf(params.get("prefix"));
        String content=String.valueOf(params.get("content"));
        if(StringUtils.isNotEmpty(prefix)){
            prefix="【"+prefix+"】";
            content=prefix+content;
            params.put("content",content);
        }
        SmsHis smsHis=new SmsHis();
        smsHis.setPhone(String.valueOf(params.get("phone")));
        smsHis.setCarrieroperator("乐云短信");
        smsHis.setContent(content);
        smsHis.setReturncode(returncode);
        SysUser user=ShiroUtils.getSysUser();
        smsHis.setYhid(String.valueOf(user.getUserId()));
        smsHis.setYhmc(user.getUserName());
        smsHisService.insertSmsHis(smsHis);
    }

    @Override
    public void onFail() {
        String returncode="0";
        String prefix=String.valueOf(params.get("prefix"));
        String content=String.valueOf(params.get("content"));
        if(StringUtils.isNotEmpty(prefix)){
            prefix="【"+prefix+"】";
            content=prefix+content;
        }
        SmsHis smsHis=new SmsHis();
        smsHis.setPhone(String.valueOf(params.get("phone")));
        smsHis.setCarrieroperator("乐云短信");
        smsHis.setContent(content);
        smsHis.setReturncode(returncode);
        SysUser user=ShiroUtils.getSysUser();
        smsHis.setYhid(String.valueOf(user.getUserId()));
        smsHis.setYhmc(user.getUserName());
        smsHisService.insertSmsHis(smsHis);
    }

    @Override
    public Map setParams(Map map) {
        params.clear();
        params.putAll(map);
        return params;
    }
}
