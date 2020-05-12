package com.ruoyi.cms.service;

import com.ruoyi.cms.domain.Attachment;
import java.util.List;

/**
 * 附件Service接口
 * 
 * @author wujiyue
 * @date 2019-11-01
 */
public interface IAttachmentService 
{
    /**
     * 查询附件
     * 
     * @param attachId 附件ID
     * @return 附件
     */
    public Attachment selectAttachmentById(String attachId);

    /**
     * 根据zid查询附件列表
     *
     * @param zid 组id
     * @return 附件集合
     */
    public List<Attachment> selectAttachmentsByZid(String zid);
    /**
     * 查询附件列表
     * 
     * @param attachment 附件
     * @return 附件集合
     */
    public List<Attachment> selectAttachmentList(Attachment attachment);

    /**
     * 新增附件
     * 
     * @param attachment 附件
     * @return 结果
     */
    public int insertAttachment(Attachment attachment);

    /**
     * 修改附件
     * 
     * @param attachment 附件
     * @return 结果
     */
    public int updateAttachment(Attachment attachment);

    /**
     * 批量删除附件
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAttachmentByIds(String ids);

    /**
     * 删除附件信息
     * 
     * @param attachId 附件ID
     * @return 结果
     */
    public int deleteAttachmentById(String attachId);
}
