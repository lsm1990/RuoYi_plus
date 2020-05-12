package com.ruoyi.spider.mapper;

import com.ruoyi.spider.domain.SpiderMission;
import java.util.List;

/**
 * 爬虫任务Mapper接口
 * 
 * @author wujiyue
 * @date 2019-11-11
 */
public interface SpiderMissionMapper 
{
    /**
     * 查询爬虫任务
     * 
     * @param missionId 爬虫任务ID
     * @return 爬虫任务
     */
    public SpiderMission selectSpiderMissionById(Long missionId);

    /**
     * 查询爬虫任务列表
     * 
     * @param spiderMission 爬虫任务
     * @return 爬虫任务集合
     */
    public List<SpiderMission> selectSpiderMissionList(SpiderMission spiderMission);

    /**
     * 新增爬虫任务
     * 
     * @param spiderMission 爬虫任务
     * @return 结果
     */
    public int insertSpiderMission(SpiderMission spiderMission);

    /**
     * 修改爬虫任务
     * 
     * @param spiderMission 爬虫任务
     * @return 结果
     */
    public int updateSpiderMission(SpiderMission spiderMission);

    /**
     * 删除爬虫任务
     * 
     * @param missionId 爬虫任务ID
     * @return 结果
     */
    public int deleteSpiderMissionById(Long missionId);

    /**
     * 批量删除爬虫任务
     * 
     * @param missionIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSpiderMissionByIds(String[] missionIds);
}
