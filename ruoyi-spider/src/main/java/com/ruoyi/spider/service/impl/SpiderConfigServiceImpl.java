package com.ruoyi.spider.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.spider.mapper.SpiderConfigMapper;
import com.ruoyi.spider.domain.SpiderConfig;
import com.ruoyi.spider.service.ISpiderConfigService;
import com.ruoyi.common.core.text.Convert;

/**
 * 爬虫配置Service业务层处理
 * 
 * @author wujiyue
 * @date 2019-11-11
 */
@Service
public class SpiderConfigServiceImpl implements ISpiderConfigService 
{
    @Autowired
    private SpiderConfigMapper spiderConfigMapper;

    /**
     * 查询爬虫配置
     * 
     * @param id 爬虫配置ID
     * @return 爬虫配置
     */
    @Override
    public SpiderConfig selectSpiderConfigById(Long id)
    {
        return spiderConfigMapper.selectSpiderConfigById(id);
    }

    @Override
    public SpiderConfig selectSpiderConfigByCode(String code) {
        return spiderConfigMapper.selectSpiderConfigByCode(code);
    }

    /**
     * 查询爬虫配置列表
     * 
     * @param spiderConfig 爬虫配置
     * @return 爬虫配置
     */
    @Override
    public List<SpiderConfig> selectSpiderConfigList(SpiderConfig spiderConfig)
    {
        return spiderConfigMapper.selectSpiderConfigList(spiderConfig);
    }

    /**
     * 新增爬虫配置
     * 
     * @param spiderConfig 爬虫配置
     * @return 结果
     */
    @Override
    public int insertSpiderConfig(SpiderConfig spiderConfig)
    {
        return spiderConfigMapper.insertSpiderConfig(spiderConfig);
    }

    /**
     * 修改爬虫配置
     * 
     * @param spiderConfig 爬虫配置
     * @return 结果
     */
    @Override
    public int updateSpiderConfig(SpiderConfig spiderConfig)
    {
        return spiderConfigMapper.updateSpiderConfig(spiderConfig);
    }

    /**
     * 删除爬虫配置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSpiderConfigByIds(String ids)
    {
        return spiderConfigMapper.deleteSpiderConfigByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除爬虫配置信息
     * 
     * @param id 爬虫配置ID
     * @return 结果
     */
    @Override
    public int deleteSpiderConfigById(Long id)
    {
        return spiderConfigMapper.deleteSpiderConfigById(id);
    }
}
