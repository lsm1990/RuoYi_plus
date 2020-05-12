package com.ruoyi.spider.mapper;

import com.ruoyi.spider.domain.SpiderFiledRule;
import java.util.List;

/**
 * 字段值处理规则Mapper接口
 * 
 * @author wujiyue
 * @date 2019-11-14
 */
public interface SpiderFiledRuleMapper 
{
    /**
     * 查询字段值处理规则
     * 
     * @param id 字段值处理规则ID
     * @return 字段值处理规则
     */
    public SpiderFiledRule selectSpiderFiledRuleById(Long id);

    /**
     * 查询字段值处理规则列表
     * 
     * @param spiderFiledRule 字段值处理规则
     * @return 字段值处理规则集合
     */
    public List<SpiderFiledRule> selectSpiderFiledRuleList(SpiderFiledRule spiderFiledRule);

    /**
     * 新增字段值处理规则
     * 
     * @param spiderFiledRule 字段值处理规则
     * @return 结果
     */
    public int insertSpiderFiledRule(SpiderFiledRule spiderFiledRule);

    /**
     * 修改字段值处理规则
     * 
     * @param spiderFiledRule 字段值处理规则
     * @return 结果
     */
    public int updateSpiderFiledRule(SpiderFiledRule spiderFiledRule);

    /**
     * 删除字段值处理规则
     * 
     * @param id 字段值处理规则ID
     * @return 结果
     */
    public int deleteSpiderFiledRuleById(Long id);

    /**
     * 批量删除字段值处理规则
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSpiderFiledRuleByIds(String[] ids);
}
