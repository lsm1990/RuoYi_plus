package com.ruoyi.third.mapper;

import com.ruoyi.third.domain.AiHis;
import java.util.List;

/**
 * 人工智能图片识别历史Mapper接口
 * 
 * @author wujiyue
 * @date 2019-10-12
 */
public interface AiHisMapper 
{
    /**
     * 查询人工智能图片识别历史
     * 
     * @param id 人工智能图片识别历史ID
     * @return 人工智能图片识别历史
     */
    public AiHis selectAiHisById(Long id);

    /**
     * 查询人工智能图片识别历史列表
     * 
     * @param aiHis 人工智能图片识别历史
     * @return 人工智能图片识别历史集合
     */
    public List<AiHis> selectAiHisList(AiHis aiHis);

    /**
     * 新增人工智能图片识别历史
     * 
     * @param aiHis 人工智能图片识别历史
     * @return 结果
     */
    public int insertAiHis(AiHis aiHis);

    /**
     * 修改人工智能图片识别历史
     * 
     * @param aiHis 人工智能图片识别历史
     * @return 结果
     */
    public int updateAiHis(AiHis aiHis);

    /**
     * 删除人工智能图片识别历史
     * 
     * @param id 人工智能图片识别历史ID
     * @return 结果
     */
    public int deleteAiHisById(Long id);

    /**
     * 批量删除人工智能图片识别历史
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAiHisByIds(String[] ids);
}
