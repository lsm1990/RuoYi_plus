package com.ruoyi.spider.mapper;

import com.ruoyi.spider.domain.SpiderField;
import java.util.List;

/**
 * 爬虫字段Mapper接口
 * 
 * @author wujiyue
 * @date 2019-11-12
 */
public interface SpiderFieldMapper 
{
    /**
     * 查询爬虫字段
     * 
     * @param fieldId 爬虫字段ID
     * @return 爬虫字段
     */
    public SpiderField selectSpiderFieldById(Long fieldId);

    /**
     * 查询爬虫字段列表
     * 
     * @param spiderField 爬虫字段
     * @return 爬虫字段集合
     */
    public List<SpiderField> selectSpiderFieldList(SpiderField spiderField);

    /**
     * 新增爬虫字段
     * 
     * @param spiderField 爬虫字段
     * @return 结果
     */
    public int insertSpiderField(SpiderField spiderField);

    /**
     * 修改爬虫字段
     * 
     * @param spiderField 爬虫字段
     * @return 结果
     */
    public int updateSpiderField(SpiderField spiderField);

    /**
     * 删除爬虫字段
     * 
     * @param fieldId 爬虫字段ID
     * @return 结果
     */
    public int deleteSpiderFieldById(Long fieldId);

    /**
     * 批量删除爬虫字段
     * 
     * @param fieldIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSpiderFieldByIds(String[] fieldIds);
}
