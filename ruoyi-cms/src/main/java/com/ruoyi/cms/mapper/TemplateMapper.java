package com.ruoyi.cms.mapper;

import com.ruoyi.cms.domain.Template;
import java.util.List;

/**
 * 模板Mapper接口
 * 
 * @author wujiyue
 * @date 2019-11-17
 */
public interface TemplateMapper 
{
    /**
     * 查询模板
     * 
     * @param templateId 模板ID
     * @return 模板
     */
    public Template selectTemplateById(Long templateId);

    /**
     * 查询模板列表
     * 
     * @param template 模板
     * @return 模板集合
     */
    public List<Template> selectTemplateList(Template template);

    /**
     * 新增模板
     * 
     * @param template 模板
     * @return 结果
     */
    public int insertTemplate(Template template);

    /**
     * 修改模板
     * 
     * @param template 模板
     * @return 结果
     */
    public int updateTemplate(Template template);

    /**
     * 删除模板
     * 
     * @param templateId 模板ID
     * @return 结果
     */
    public int deleteTemplateById(Long templateId);

    /**
     * 批量删除模板
     * 
     * @param templateIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTemplateByIds(String[] templateIds);
}
