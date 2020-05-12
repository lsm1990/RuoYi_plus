package com.ruoyi.third.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.third.mapper.AiHisMapper;
import com.ruoyi.third.domain.AiHis;
import com.ruoyi.third.service.IAiHisService;
import com.ruoyi.common.core.text.Convert;

/**
 * 人工智能图片识别历史Service业务层处理
 * 
 * @author wujiyue
 * @date 2019-10-12
 */
@Service
public class AiHisServiceImpl implements IAiHisService 
{
    @Autowired
    private AiHisMapper aiHisMapper;

    /**
     * 查询人工智能图片识别历史
     * 
     * @param id 人工智能图片识别历史ID
     * @return 人工智能图片识别历史
     */
    @Override
    public AiHis selectAiHisById(Long id)
    {
        return aiHisMapper.selectAiHisById(id);
    }

    /**
     * 查询人工智能图片识别历史列表
     * 
     * @param aiHis 人工智能图片识别历史
     * @return 人工智能图片识别历史
     */
    @Override
    public List<AiHis> selectAiHisList(AiHis aiHis)
    {
        return aiHisMapper.selectAiHisList(aiHis);
    }

    /**
     * 新增人工智能图片识别历史
     * 
     * @param aiHis 人工智能图片识别历史
     * @return 结果
     */
    @Override
    public int insertAiHis(AiHis aiHis)
    {
        aiHis.setCreateTime(DateUtils.getNowDate());
        return aiHisMapper.insertAiHis(aiHis);
    }

    /**
     * 修改人工智能图片识别历史
     * 
     * @param aiHis 人工智能图片识别历史
     * @return 结果
     */
    @Override
    public int updateAiHis(AiHis aiHis)
    {
        aiHis.setUpdateTime(DateUtils.getNowDate());
        return aiHisMapper.updateAiHis(aiHis);
    }

    /**
     * 删除人工智能图片识别历史对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAiHisByIds(String ids)
    {
        return aiHisMapper.deleteAiHisByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除人工智能图片识别历史信息
     * 
     * @param id 人工智能图片识别历史ID
     * @return 结果
     */
    @Override
    public int deleteAiHisById(Long id)
    {
        return aiHisMapper.deleteAiHisById(id);
    }
}
