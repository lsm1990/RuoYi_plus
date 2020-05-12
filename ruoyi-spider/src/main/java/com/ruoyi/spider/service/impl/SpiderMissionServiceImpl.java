package com.ruoyi.spider.service.impl;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import com.google.common.collect.Maps;
import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.ICallBack;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.spider.backend.SpiderBackendService;
import com.ruoyi.spider.config.SpiderConstants;
import com.ruoyi.spider.domain.SpiderConfig;
import com.ruoyi.spider.domain.SpiderField;
import com.ruoyi.spider.mapper.SpiderConfigMapper;
import com.ruoyi.spider.mapper.SpiderFieldMapper;
import com.ruoyi.spider.processer.AbstractProcessor;
import com.ruoyi.spider.processer.DefalutProcessor;
import com.ruoyi.system.domain.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.ruoyi.spider.mapper.SpiderMissionMapper;
import com.ruoyi.spider.domain.SpiderMission;
import com.ruoyi.spider.service.ISpiderMissionService;
import com.ruoyi.common.core.text.Convert;

/**
 * 爬虫任务Service业务层处理
 * 
 * @author wujiyue
 * @date 2019-11-11
 */
@Service
public class SpiderMissionServiceImpl implements ISpiderMissionService 
{
    @Autowired
    private SpiderMissionMapper spiderMissionMapper;
    @Autowired
    private SpiderConfigMapper spiderConfigMapper;

    @Autowired
    private SpiderFieldMapper spiderFieldMapper;

    /**
     * 查询爬虫任务
     * 
     * @param missionId 爬虫任务ID
     * @return 爬虫任务
     */
    @Override
    public SpiderMission selectSpiderMissionById(Long missionId)
    {
        return spiderMissionMapper.selectSpiderMissionById(missionId);
    }

    /**
     * 查询爬虫任务列表
     * 
     * @param spiderMission 爬虫任务
     * @return 爬虫任务
     */
    @Override
    @DataScope(deptAlias = "a",userAlias = "a")
    public List<SpiderMission> selectSpiderMissionList(SpiderMission spiderMission)
    {
        return spiderMissionMapper.selectSpiderMissionList(spiderMission);
    }

    /**
     * 新增爬虫任务
     * 
     * @param spiderMission 爬虫任务
     * @return 结果
     */
    @Override
    public int insertSpiderMission(SpiderMission spiderMission)
    {
        SysUser user= ShiroUtils.getSysUser();
        spiderMission.setUserId(user.getUserId().toString());
        spiderMission.setDeptId(user.getDeptId().toString());
        spiderMission.setStatus(SpiderConstants.SPIDER_MISSION_STATUS_WAIT);
        spiderMission.setCreateBy(user.getUserName());
        spiderMission.setCreateTime(DateUtils.getNowDate());
        return spiderMissionMapper.insertSpiderMission(spiderMission);
    }

    /**
     * 修改爬虫任务
     * 
     * @param spiderMission 爬虫任务
     * @return 结果
     */
    @Override
    public int updateSpiderMission(SpiderMission spiderMission)
    {
        return spiderMissionMapper.updateSpiderMission(spiderMission);
    }

    /**
     * 删除爬虫任务对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSpiderMissionByIds(String ids)
    {
        return spiderMissionMapper.deleteSpiderMissionByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除爬虫任务信息
     * 
     * @param missionId 爬虫任务ID
     * @return 结果
     */
    @Override
    public int deleteSpiderMissionById(Long missionId)
    {
        return spiderMissionMapper.deleteSpiderMissionById(missionId);
    }

    @Override
    public AjaxResult runSpiderMission(String missionId) {
        SpiderMission mission=this.selectSpiderMissionById(Long.valueOf(missionId));

        if(mission!=null){
            if(SpiderConstants.SPIDER_MISSION_STATUS_RUNNING.equals(mission.getStatus())){
                return AjaxResult.error("该任务正在运行中!");
            }
           /* Long configId=mission.getSpiderConfigId();
            SpiderConfig config = spiderConfigMapper.selectSpiderConfigById(configId);
            SpiderField queryForm=new SpiderField();
            queryForm.setConfigId(config.getId());
            List<SpiderField> fields = spiderFieldMapper.selectSpiderFieldList(queryForm);
            config.setFieldsList(fields);
            String entryUrls=mission.getEntryUrls();
            List<String> urls= Lists.newArrayList();
            if(StringUtils.isNotEmpty(entryUrls)){
                String[] arr=entryUrls.split(",");
                for(String s:arr){
                    if(StringUtils.isNotEmpty(s)){
                        urls.add(s);
                    }
                }
            }
            config.setEntryUrlsList(urls);
            config.setExitWay(mission.getExitWay());
            Long c= mission.getExitWayCount();
            if(c==null){
                c=0L;
            }
            config.setCount(Integer.valueOf(c.toString()));
            AbstractProcessor processor=new DefalutProcessor(config,missionId.toString());

            mission.setStatus(SpiderConstants.SPIDER_MISSION_STATUS_RUNNING);
            mission.setStartTime(new Date());
            spiderMissionMapper.updateSpiderMission(mission);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    CopyOnWriteArrayList<LinkedHashMap<String, String>> datas = processor.execute();
                    mission.setEndTime(new Date());
                    mission.setStatus(SpiderConstants.SPIDER_MISSION_STATUS_DONE);
                    mission.setSuccessNum(Long.valueOf(datas.size()));
                    Long count=(mission.getEndTime().getTime()-mission.getStartTime().getTime())/1000;
                    mission.setTimeCost(count.toString());
                    spiderMissionMapper.updateSpiderMission(mission);
                }
            }).start();*/
            SpiderCallBack spiderCallBack= new SpiderCallBack();
            SpiderBackendService spiderBackendService=new SpiderBackendService(missionId,spiderCallBack);
            spiderBackendService.start();
        }
        return AjaxResult.success();
    }

    public class SpiderCallBack implements ICallBack{
        Map params= Maps.newConcurrentMap();
        @Override
        public void onSuccess() {
            System.out.println(">>>>>>>>>>>>>done>>>>>>>>>>>>>>");
            CopyOnWriteArrayList<LinkedHashMap<String, String>> datas=(CopyOnWriteArrayList<LinkedHashMap<String, String>>)params.get("datas");
            System.out.println(">>>>>>>>>>>>>"+datas.size()+">>>>>>>>>>>>>>");
        }

        @Override
        public void onFail() {

        }

        @Override
        public Map setParams(Map map) {
            params.clear();
            params.putAll(map);
            return params;
        }
    }
}
