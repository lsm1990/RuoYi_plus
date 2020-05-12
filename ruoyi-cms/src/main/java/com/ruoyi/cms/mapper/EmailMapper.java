package com.ruoyi.cms.mapper;

import com.ruoyi.cms.domain.Email;
import java.util.List;

/**
 * 邮件Mapper接口
 * 
 * @author wujiyue
 * @date 2019-11-04
 */
public interface EmailMapper 
{
    /**
     * 查询邮件
     * 
     * @param id 邮件ID
     * @return 邮件
     */
    public Email selectEmailById(String id);

    /**
     * 查询邮件列表
     * 
     * @param email 邮件
     * @return 邮件集合
     */
    public List<Email> selectEmailList(Email email);

    /**
     * 新增邮件
     * 
     * @param email 邮件
     * @return 结果
     */
    public int insertEmail(Email email);

    /**
     * 修改邮件
     * 
     * @param email 邮件
     * @return 结果
     */
    public int updateEmail(Email email);

    /**
     * 删除邮件
     * 
     * @param id 邮件ID
     * @return 结果
     */
    public int deleteEmailById(Long id);

    /**
     * 批量删除邮件
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteEmailByIds(String[] ids);
}
