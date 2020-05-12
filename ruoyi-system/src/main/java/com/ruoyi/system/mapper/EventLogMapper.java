package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.EventLog;
import java.util.List;

/**
 * 事件日志Mapper接口
 * 
 * @author ruoyi
 * @date 2019-12-30
 */
public interface EventLogMapper 
{
    /**
     * 查询事件日志
     * 
     * @param id 事件日志ID
     * @return 事件日志
     */
    public EventLog selectEventLogById(Long id);

    /**
     * 查询事件日志列表
     * 
     * @param eventLog 事件日志
     * @return 事件日志集合
     */
    public List<EventLog> selectEventLogList(EventLog eventLog);

    /**
     * 新增事件日志
     * 
     * @param eventLog 事件日志
     * @return 结果
     */
    public int insertEventLog(EventLog eventLog);

    /**
     * 修改事件日志
     * 
     * @param eventLog 事件日志
     * @return 结果
     */
    public int updateEventLog(EventLog eventLog);

    /**
     * 删除事件日志
     * 
     * @param id 事件日志ID
     * @return 结果
     */
    public int deleteEventLogById(Long id);

    /**
     * 批量删除事件日志
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteEventLogByIds(String[] ids);
}
