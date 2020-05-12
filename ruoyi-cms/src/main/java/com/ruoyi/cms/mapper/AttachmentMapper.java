package com.ruoyi.cms.mapper;

import com.ruoyi.cms.domain.Attachment;
import java.util.List;

/**
 * 附件Mapper接口
 * 
 * @author wujiyue
 * @date 2019-11-01
 */
public interface AttachmentMapper 
{
    /**
     * 查询附件
     * 
     * @param attachId 附件ID
     * @return 附件
     */
    public Attachment selectAttachmentById(String attachId);

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
     * 删除附件
     * 
     * @param attachId 附件ID
     * @return 结果
     */
    public int deleteAttachmentById(String attachId);

    /**
     * 批量删除附件
     * 
     * @param attachIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteAttachmentByIds(String[] attachIds);
}
