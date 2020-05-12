package com.ruoyi.cms.service;

import cn.hutool.cache.Cache;
import cn.hutool.cache.CacheUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.ruoyi.cms.domain.*;
import com.ruoyi.cms.util.CmsConstants;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.sql.SqlUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service("cms")
public class CmsService {

    @Autowired
    private IFriendLinkService friendLinkService;

    @Autowired
    private IWebSiteService webSiteService;
    @Autowired
    private ITagsService tagsService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IArticleService articleService;
    @Autowired
    private IAlbumService  albumService;
    @Autowired
    private IShortWordsService shortWordsService;
    @Autowired
    private ICommentService commentService;
    @Autowired
    private IResourceService resourceService;


    private Cache<String,Object> siteInfoCache= CacheUtil.newTimedCache(1000*60*10);//10分钟有效时间，过期后重新数据库取值
    private static final String KEY_SITE_INFO="siteInfo";
    private static final String KEY_FRIEND_LINKS="friendLinks";
    private static final String KEY_TAGS="tags";
    private static final String KEY_CATEGORY="category";
    private static final String KEY_INDEX_LIST="indexList";

    private static final String KEY_RECENT_LIST="recentList";
    private static final String KEY_RECOMMENDED_LIST="recommendedList";
    private static final String KEY_HOT_LIST="hotList";
    private static final String KEY_TOP_LIST="topList";
    private static final String KEY_FOCUS_LIST="focusList";
    private static final String KEY_NEW_COMMENT_LIST="newCommentList";

    private static final String KEY_RECOMMENDED_LIST_RES="recommendedList_res";//推荐资源
    /**
     * 清空前台缓存
     */
    public void clearCache(){
        siteInfoCache.clear();
    }

    public Object getFriendLinks(){
       List<FriendLink> list= (List<FriendLink>) siteInfoCache.get(KEY_FRIEND_LINKS,false);
       if(list==null){
           FriendLink form= new FriendLink();
           form.setAuditState(CmsConstants.AUDIT_STATE_AGREE);
           list= friendLinkService.selectFriendLinkList(form);
           siteInfoCache.put(KEY_FRIEND_LINKS,list);
       }
       return list;
    }

    public Object getSiteInfo(){
        Map map=(Map)siteInfoCache.get(KEY_SITE_INFO,false);//注意第二个参数false表示每次取值后定时时间不重新计算
        if(map==null){
            map = webSiteService.getSiteInfo();
            siteInfoCache.put(KEY_SITE_INFO,map);
        }
        return map;
    }

    /**
     * 获得标签云
     * @return
     */
    public Object getTagsCloud(){
        List<Tags> list = ( List<Tags>)siteInfoCache.get(KEY_TAGS,false);
        if(list==null){
            list = tagsService.selectTagsAll();
            siteInfoCache.put(KEY_TAGS,list);
        }
        return list;
    }

    /**
     * 查询导航栏目
     * @return
     */
    public Object selectNavCategories(){
        List<Category> list=( List<Category>)siteInfoCache.get(KEY_CATEGORY,false);
        if(list==null){
            Category queryForm=new Category();
            list =categoryService.selectNavCategories(queryForm);
            siteInfoCache.put(KEY_CATEGORY,list);
        }

        return list;
    }

    /**
     * 首页的文章列表
     * @return
     */
    public List<Article> indexList(Integer limit){
        if(limit==null){
            limit=1;
        }
        List<Article> list=( List<Article>)siteInfoCache.get(KEY_INDEX_LIST,false);
        if(list==null){
            Article form=new Article();
            list=articleService.selectArticlesRegionNotNull(form);
            siteInfoCache.put(KEY_INDEX_LIST,list);
        }
        if(CollectionUtils.isNotEmpty(list)){
            //这类文章一般不会太多，全部取出来之后，随机取limit条
            if(list.size()>limit){
                Collections.shuffle(list);
                list=list.subList(0,limit);
            }
        }
        return list;
    }


    /**
     * 最新文章列表
     * @return
     */
    public List<Article> recentList(Integer limit){
        if(limit==null){
            limit=1;
        }
        List<Article> list=( List<Article>)siteInfoCache.get(KEY_RECENT_LIST,false);
        if(list==null){
            list=articleService.selectArticlesByArticleRegionType(ArticleRegionType.REGION_NEW);
            siteInfoCache.put(KEY_RECENT_LIST,list);
        }
        if(CollectionUtils.isNotEmpty(list)){
            //这类文章一般不会太多，全部取出来之后，随机取limit条
            if(list.size()>limit){
                Collections.shuffle(list);
                list=list.subList(0,limit);
            }
        }
        return list;
    }
    /**
     * 推荐文章列表
     * @return
     */
    public List<Article> recommendedList(Integer limit){
        if(limit==null){
            limit=1;
        }
        List<Article> list=( List<Article>)siteInfoCache.get(KEY_RECOMMENDED_LIST,false);
        if(list==null){
            list=articleService.selectArticlesByArticleRegionType(ArticleRegionType.REGION_RECOMMEND);
            siteInfoCache.put(KEY_RECOMMENDED_LIST,list);
        }
        if(CollectionUtils.isNotEmpty(list)){
            //这类文章一般不会太多，全部取出来之后，随机取limit条
            if(list.size()>limit){
                Collections.shuffle(list);
                list=list.subList(0,limit);
            }
        }
        return list;
    }

    /**
     * 热点文章列表
     * @return
     */
    public List<Article> hotList(Integer limit){
        if(limit==null){
            limit=1;
        }
        List<Article> list=( List<Article>)siteInfoCache.get(KEY_HOT_LIST,false);
        if(list==null){
            list=articleService.selectArticlesByArticleRegionType(ArticleRegionType.REGION_HOT);
            siteInfoCache.put(KEY_HOT_LIST,list);
        }
        if(CollectionUtils.isNotEmpty(list)){
            //这类文章一般不会太多，全部取出来之后，随机取limit条
            if(list.size()>limit){
                Collections.shuffle(list);
                list=list.subList(0,limit);
            }
        }
        return list;
    }

    /**
     * 置顶文章列表
     * @return
     */
    public List<Article> topList(Integer limit){
        if(limit==null){
            limit=1;
        }
        List<Article> list=( List<Article>)siteInfoCache.get(KEY_TOP_LIST,false);
        if(list==null){
            list=articleService.selectArticlesByArticleRegionType(ArticleRegionType.REGION_TOP);
            siteInfoCache.put(KEY_TOP_LIST,list);
        }
        if(CollectionUtils.isNotEmpty(list)){
            //头条文章一般不会太多，全部取出来之后，随机取limit条
            if(list.size()>limit){
                Collections.shuffle(list);
                list=list.subList(0,limit);
            }
        }
        return list;
    }

    /**
     * 焦点列表
     * @return
     */
    public List<Article> focusList(Integer limit){
        if(limit==null){
            limit=1;
        }
        List<Article> list=( List<Article>)siteInfoCache.get(KEY_FOCUS_LIST,false);
        if(list==null){
            list=articleService.selectArticlesByArticleRegionType(ArticleRegionType.REGION_FOCUS);
            siteInfoCache.put(KEY_FOCUS_LIST,list);
        }
        if(CollectionUtils.isNotEmpty(list)){
            //焦点文章一般不会太多，全部取出来之后，随机取limit条
            if(list.size()>limit){
                Collections.shuffle(list);
                list=list.subList(0,limit);
            }
        }
        return list;
    }


    private static final String KEY_FULL_TABS="fullTabs";
    private static final String KEY_SHORT_WORDS="shortWords";
    public List<Tags> selectFullTabs(){
        List<Tags> list=( List<Tags>)siteInfoCache.get(KEY_FULL_TABS,false);
        if(list==null){
            list = tagsService.selectBlogTabs();
            siteInfoCache.put(KEY_FULL_TABS,list);
        }
       return list;
    }
    public List<String> selectShortWords(Integer limit){
        List<ShortWords> list=( List<ShortWords>)siteInfoCache.get(KEY_SHORT_WORDS);
        if(CollectionUtils.isEmpty(list)){
            list = shortWordsService.selectShortWordsList(new ShortWords());
            siteInfoCache.put(KEY_SHORT_WORDS,list);
        }
        if(CollectionUtils.isNotEmpty(list)){
            //焦点文章一般不会太多，全部取出来之后，随机取limit条
            if(list.size()>limit){
                Collections.shuffle(list);
                list=list.subList(0,limit);
            }
        }
        List<String> res= Lists.newArrayList();
        list.forEach(s->{
            res.add(s.getShortWords());
        });
        return res;
    }
    /**
     * 注意:这个获取banner是为了给pnews这个前台模版准备的，它需要直接获取banner配置的图片素材
     * 不要和BlogController里面的混淆了
     */
    public Object selectIndexBannerImgs(String code){
        if(StringUtils.isEmpty(code)){
            code="blog_index";
        }
        Map data=(Map) siteInfoCache.get(code,false);
        if(data==null){
            data= albumService.getAlbum(code);
            siteInfoCache.put(code,data);
        }
        if(data!=null){
            Album album = JSON.parseObject(JSON.toJSONString(data),Album.class);
            List<AlbumMaterial> images=album.getImages();
            return images;
        }
        return null;
    }

    /************************资源********** start ************/

    /**
     * 推荐文章列表
     * @return
     */
    public List<Resource> recommendedResourceList(Integer limit){
        if(limit==null){
            limit=1;
        }
        List<Resource> list=( List<Resource>)siteInfoCache.get(KEY_RECOMMENDED_LIST_RES,false);
        if(CollectionUtils.isEmpty(list)){
            Resource form=new Resource();
            form.setResourceRegion(ResourceRegionType.REGION_RECOMMEND.getVal());
            form.setAuditState(CmsConstants.AUDIT_STATE_AGREE.toString());
            form.setStatus(CmsConstants.STATUS_NORMAL);
            list=resourceService.selectResourceList(form);
            siteInfoCache.put(KEY_RECOMMENDED_LIST_RES,list);
        }
        if(CollectionUtils.isNotEmpty(list)){
            //这类文章一般不会太多，全部取出来之后，随机取limit条
            if(list.size()>limit){
                Collections.shuffle(list);
                list=list.subList(0,limit);
            }
        }
        return list;
    }
    /************************资源********** end ************/
        //Avatar模板首页获取文章和资源列表
    public Object avatarIndexList(Integer limit) {
        if (limit == null) {
            limit = 10;
        }
        //8个文章+2个资源
        List<Article> articleList =this.indexList(8);
        articleList.forEach(a->{
            a.setExtraName("article");
        });
        List<Resource> resourceList =this.recommendedResourceList(2);
        resourceList.forEach(r->{
            r.setExtraName("resource");
        });
        List<Object> res=Lists.newArrayList();
        res.addAll(articleList);
        res.addAll(resourceList);
        Collections.shuffle(res);
        return res;
    }
    /**
     * 最新留言
     */
    public List<Comment> newComments(Integer limit){

        List<Comment> list=(List<Comment>)siteInfoCache.get(KEY_NEW_COMMENT_LIST);
        if(CollectionUtils.isEmpty(list)) {
            Comment form = new Comment();
            form.setType(CmsConstants.COMMENT_TYPE_LIUYAN);
            form.setStatus(CmsConstants.STATUS_NORMAL);
            Integer pageNum = 1;
            Integer pageSize = limit;
            if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
                PageHelper.startPage(pageNum, pageSize);
            }
            list = commentService.selectCommentList(form);
            siteInfoCache.put(KEY_NEW_COMMENT_LIST,list);
        }
        return list;
    }
}
