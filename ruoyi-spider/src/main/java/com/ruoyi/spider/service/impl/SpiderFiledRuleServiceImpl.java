package com.ruoyi.spider.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.spider.mapper.SpiderFiledRuleMapper;
import com.ruoyi.spider.domain.SpiderFiledRule;
import com.ruoyi.spider.service.ISpiderFiledRuleService;
import com.ruoyi.common.core.text.Convert;

/**
 * 字段值处理规则Service业务层处理
 * 
 * @author wujiyue
 * @date 2019-11-14
 */
@Service
public class SpiderFiledRuleServiceImpl implements ISpiderFiledRuleService 
{
    @Autowired
    private SpiderFiledRuleMapper spiderFiledRuleMapper;

    /**
     * 查询字段值处理规则
     * 
     * @param id 字段值处理规则ID
     * @return 字段值处理规则
     */
    @Override
    public SpiderFiledRule selectSpiderFiledRuleById(Long id)
    {
        return spiderFiledRuleMapper.selectSpiderFiledRuleById(id);
    }

    /**
     * 查询字段值处理规则列表
     * 
     * @param spiderFiledRule 字段值处理规则
     * @return 字段值处理规则
     */
    @Override
    public List<SpiderFiledRule> selectSpiderFiledRuleList(SpiderFiledRule spiderFiledRule)
    {
        return spiderFiledRuleMapper.selectSpiderFiledRuleList(spiderFiledRule);
    }

    /**
     * 新增字段值处理规则
     * 
     * @param spiderFiledRule 字段值处理规则
     * @return 结果
     */
    @Override
    public int insertSpiderFiledRule(SpiderFiledRule spiderFiledRule)
    {
        return spiderFiledRuleMapper.insertSpiderFiledRule(spiderFiledRule);
    }

    /**
     * 修改字段值处理规则
     * 
     * @param spiderFiledRule 字段值处理规则
     * @return 结果
     */
    @Override
    public int updateSpiderFiledRule(SpiderFiledRule spiderFiledRule)
    {
        return spiderFiledRuleMapper.updateSpiderFiledRule(spiderFiledRule);
    }

    /**
     * 删除字段值处理规则对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSpiderFiledRuleByIds(String ids)
    {
        return spiderFiledRuleMapper.deleteSpiderFiledRuleByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除字段值处理规则信息
     * 
     * @param id 字段值处理规则ID
     * @return 结果
     */
    @Override
    public int deleteSpiderFiledRuleById(Long id)
    {
        return spiderFiledRuleMapper.deleteSpiderFiledRuleById(id);
    }
}
