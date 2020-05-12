package com.ruoyi.spider.backend;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.ICallBack;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.spider.config.SpiderConstants;
import com.ruoyi.spider.domain.SpiderConfig;
import com.ruoyi.spider.domain.SpiderField;
import com.ruoyi.spider.domain.SpiderFiledRule;
import com.ruoyi.spider.domain.SpiderMission;
import com.ruoyi.spider.mapper.SpiderFieldMapper;
import com.ruoyi.spider.mapper.SpiderFiledRuleMapper;
import com.ruoyi.spider.processer.AbstractProcessor;
import com.ruoyi.spider.processer.DefalutProcessor;
import com.ruoyi.spider.service.ISpiderConfigService;
import com.ruoyi.spider.service.ISpiderMissionService;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 任务爬虫入口类，通过传入爬虫任务id，从数据库中查询配置参数
 * 该种方式的爬虫相比快速配置的FastSpiderBackendService来比，它可以设置字段值处理规则。
 */
public class SpiderBackendService extends Thread {
    protected final Logger logger = LoggerFactory.getLogger(SpiderBackendService.class);
    private String missionId;
    private ICallBack callBack;


    private ISpiderMissionService spiderMissionService= SpringUtils.getBean(ISpiderMissionService.class);//爬虫任务

    private ISpiderConfigService spiderConfigService=SpringUtils.getBean(ISpiderConfigService.class);//爬虫配置

    private SpiderFieldMapper spiderFieldMapper=SpringUtils.getBean(SpiderFieldMapper.class);//爬虫字段

    private SpiderFiledRuleMapper spiderFiledRuleMapper=SpringUtils.getBean(SpiderFiledRuleMapper.class);//爬虫字段值处理规则

    public SpiderBackendService(String missionId){
        this.missionId=missionId;
    }

    public SpiderBackendService(String missionId,ICallBack callBack){
        this.missionId=missionId;
        this.callBack=callBack;
    }
    @Override
    public void run() {
        SpiderMission mission=spiderMissionService.selectSpiderMissionById(Long.valueOf(missionId));
        if(mission!=null){
            if(SpiderConstants.SPIDER_MISSION_STATUS_RUNNING.equals(mission.getStatus())){
                logger.warn(">>>>>>>>>>>>>>>爬虫任务["+missionId+"]已经在运行!本次不在执行!<<<<<<<<<");
                return;
            }
            //查询爬虫配置
            Long configId=mission.getSpiderConfigId();
            SpiderConfig config = spiderConfigService.selectSpiderConfigById(configId);
            //查询字段配置
            SpiderField queryForm=new SpiderField();
            queryForm.setConfigId(config.getId());
            List<SpiderField> fields = spiderFieldMapper.selectSpiderFieldList(queryForm);
            config.setFieldsList(fields);
            //设置字段值处理规则
            for(SpiderField field:fields){
                SpiderFiledRule ruleQueryForm=new SpiderFiledRule();
                ruleQueryForm.setFieldId(field.getFieldId().toString());
                List<SpiderFiledRule> rules = spiderFiledRuleMapper.selectSpiderFiledRuleList(ruleQueryForm);
                field.setFieldRules(rules);
            }
            //设置入口地址
            String entryUrls=mission.getEntryUrls();
            List<String> urls= Lists.newArrayList();
            if(StringUtils.isNotEmpty(entryUrls)){
                String[] arr=entryUrls.split(",");
                for(String s:arr){
                    if(StringUtils.isNotEmpty(s)&&isURL(s)){
                        urls.add(s);
                    }
                    if(StringUtils.isNotEmpty(s)&&!isURL(s)){
                        logger.warn(">>>>>>>>>>>>>>>配置的url:["+s+"]不是一个有效的url!");
                    }
                }
            }
            config.setEntryUrlsList(urls);

            //设置退出方式
            config.setExitWay(mission.getExitWay());
            Long c= mission.getExitWayCount();
            if(c==null){
                c=0L;
            }
            config.setCount(Integer.valueOf(c.toString()));
            if(StringUtils.isNotEmpty(mission.getHeaderStr())){
                config.setHeader(mission.getHeaderStr());
            }
            if(StringUtils.isNotEmpty(mission.getCookieStr())){
                config.setCookie(mission.getCookieStr());
            }
            AbstractProcessor processor=new DefalutProcessor(config,missionId.toString());

            mission.setStatus(SpiderConstants.SPIDER_MISSION_STATUS_RUNNING);
            mission.setStartTime(new Date());
            spiderMissionService.updateSpiderMission(mission);

            CopyOnWriteArrayList<LinkedHashMap<String, String>> datas = processor.execute();//执行爬虫
            mission.setEndTime(new Date());
            mission.setStatus(SpiderConstants.SPIDER_MISSION_STATUS_DONE);
            mission.setSuccessNum(Long.valueOf(datas.size()));
            Long count=(mission.getEndTime().getTime()-mission.getStartTime().getTime())/1000;
            mission.setTimeCost(count.toString());
            spiderMissionService.updateSpiderMission(mission);
            if(callBack!=null){
                Map<String,CopyOnWriteArrayList<LinkedHashMap<String, String>>> rmap=new HashMap();
                rmap.put("datas",datas);
                callBack.setParams(rmap);
                callBack.onSuccess();
            }
        }
    }

    private static boolean isURL(String str){
        str = str.toLowerCase();
        String regex = "(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]";
        return str.matches(regex);
    }
}
