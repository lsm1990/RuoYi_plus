package com.ruoyi.third.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.third.mapper.SmsHisMapper;
import com.ruoyi.third.domain.SmsHis;
import com.ruoyi.third.service.ISmsHisService;
import com.ruoyi.common.core.text.Convert;

/**
 * 短信发送历史Service业务层处理
 * 
 * @author wujiyue
 * @date 2019-10-11
 */
@Service
public class SmsHisServiceImpl implements ISmsHisService 
{
    @Autowired
    private SmsHisMapper smsHisMapper;

    /**
     * 查询短信发送历史
     * 
     * @param id 短信发送历史ID
     * @return 短信发送历史
     */
    @Override
    public SmsHis selectSmsHisById(Long id)
    {
        return smsHisMapper.selectSmsHisById(id);
    }

    /**
     * 查询短信发送历史列表
     * 
     * @param smsHis 短信发送历史
     * @return 短信发送历史
     */
    @Override
    public List<SmsHis> selectSmsHisList(SmsHis smsHis)
    {
        return smsHisMapper.selectSmsHisList(smsHis);
    }

    /**
     * 新增短信发送历史
     * 
     * @param smsHis 短信发送历史
     * @return 结果
     */
    @Override
    public int insertSmsHis(SmsHis smsHis)
    {
        return smsHisMapper.insertSmsHis(smsHis);
    }

    /**
     * 修改短信发送历史
     * 
     * @param smsHis 短信发送历史
     * @return 结果
     */
    @Override
    public int updateSmsHis(SmsHis smsHis)
    {
        return smsHisMapper.updateSmsHis(smsHis);
    }

    /**
     * 删除短信发送历史对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSmsHisByIds(String ids)
    {
        return smsHisMapper.deleteSmsHisByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除短信发送历史信息
     * 
     * @param id 短信发送历史ID
     * @return 结果
     */
    @Override
    public int deleteSmsHisById(Long id)
    {
        return smsHisMapper.deleteSmsHisById(id);
    }
}
