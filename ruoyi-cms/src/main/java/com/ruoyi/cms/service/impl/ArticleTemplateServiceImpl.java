package com.ruoyi.cms.service.impl;

import java.util.List;

import cn.hutool.cache.Cache;
import cn.hutool.cache.CacheUtil;
import com.google.common.collect.Lists;
import com.ruoyi.cms.domain.Tags;
import com.ruoyi.cms.mapper.TagsMapper;
import com.ruoyi.cms.util.CmsConstants;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.cms.mapper.ArticleTemplateMapper;
import com.ruoyi.cms.domain.ArticleTemplate;
import com.ruoyi.cms.service.IArticleTemplateService;
import com.ruoyi.common.core.text.Convert;

/**
 * 文章模板Service业务层处理
 * 
 * @author markbro
 * @date 2019-12-31
 */
@Service
public class ArticleTemplateServiceImpl implements IArticleTemplateService 
{
    @Autowired
    private ArticleTemplateMapper articleTemplateMapper;
    @Autowired
    private TagsMapper tagsMapper;

    private Cache<String, Tags> tagCache= CacheUtil.newLFUCache(100);
    /**
     * 查询文章模板
     * 
     * @param id 文章模板ID
     * @return 文章模板
     */
    @Override
    public ArticleTemplate selectArticleTemplateById(Long id)
    {
        return articleTemplateMapper.selectArticleTemplateById(id);
    }

    /**
     * 查询文章模板列表
     * 
     * @param articleTemplate 文章模板
     * @return 文章模板
     */
    @Override
    public List<ArticleTemplate> selectArticleTemplateList(ArticleTemplate articleTemplate)
    {

        List<ArticleTemplate> articleTemplates=articleTemplateMapper.selectArticleTemplateList(articleTemplate);
        selectTags(articleTemplates);
        return articleTemplates;
    }

    /**
     * 新增文章模板
     * 
     * @param articleTemplate 文章模板
     * @return 结果
     */
    @Override
    public int insertArticleTemplate(ArticleTemplate articleTemplate)
    {
        SysUser user= ShiroUtils.getSysUser();
        articleTemplate.setUserId(user.getUserId().toString());
        articleTemplate.setUserName(user.getUserName());
        articleTemplate.setCreateTime(DateUtils.getNowDate());
        articleTemplate.setAudit(0);
        return articleTemplateMapper.insertArticleTemplate(articleTemplate);
    }

    /**
     * 修改文章模板
     * 
     * @param articleTemplate 文章模板
     * @return 结果
     */
    @Override
    public int updateArticleTemplate(ArticleTemplate articleTemplate)
    {
        return articleTemplateMapper.updateArticleTemplate(articleTemplate);
    }

    /**
     * 删除文章模板对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteArticleTemplateByIds(String ids)
    {
        return articleTemplateMapper.deleteArticleTemplateByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除文章模板信息
     * 
     * @param id 文章模板ID
     * @return 结果
     */
    @Override
    public int deleteArticleTemplateById(Long id)
    {
        return articleTemplateMapper.deleteArticleTemplateById(id);
    }

    @Override
    public List<Tags> selectArticleTemplateTags(String tagIds) {
        List<Tags> tags = articleTemplateMapper.selectArticleTemplateTags();
        if(StringUtils.isEmpty(tagIds)){
            return tags;
        }
        if(tagIds.endsWith(",")){
            tagIds=tagIds.substring(0,tagIds.length()-1);
        }
        String[] arr=Convert.toStrArray(tagIds);
        List<String> list= Lists.newArrayList(arr);
        tags.forEach(t->{
            if(list.contains(t.getTagId().toString())){
                t.setSelected(true);
            }
        });
        return tags;
    }


    private void selectTags(List<ArticleTemplate> articleTemplates){

        //转换标签名称，这部分可以使用缓存提升性能
        articleTemplates.forEach(a->{
            String tagsStr=a.getTags();
            if(StringUtils.isNotEmpty(tagsStr)){
                String[] tagsArr=Convert.toStrArray(tagsStr);
                String tags_name="";
                List<Tags> tags= Lists.newArrayList();
                for(String id:tagsArr){
                    if(StringUtils.isNotEmpty(id)){

                        Tags tag=tagCache.get(id);
                        if(tag==null){
                            tag=tagsMapper.selectTagsById(Long.valueOf(id));
                            tagCache.put(id,tag);
                        }
                        tags.add(tag);
                        if(tag!=null){
                            tags_name+=tag.getTagName()+",";
                        }
                    }
                }
                if(tags_name.endsWith(",")){
                    tags_name=StringUtils.substring(tags_name,0,tags_name.length()-1);
                }
                a.setTagNames(tags_name);
                //a.setTagList(tags);
            }
        });



    }
}
