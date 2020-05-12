package com.ruoyi.cms.util;

/**
 * cms模块常量
 */
public class CmsConstants {

    /**
     * 使用状态：0-未使用
     */
    public static final String USE_STATE_NO = "0";
    /**
     * 使用状态：1-使用中
     */
    public static final String USE_STATE_YES = "1";
    /*专辑素材使用表的表名*/
    public static final String MATERIAL_USE_ALBUM_TABLE_NAME="cms_album_material";
    /*专辑素材使用表的列名*/
    public static final String MATERIAL_USE_ALBUM_COLUMN_NAME="album_id";


    public static final String MATERIAL_USE_AD_TABLE_NAME="cms_ad_material";

    public static final String MATERIAL_USE_AD_COLUMN_NAME="ad_id";

    /**
     * 审批状态：0-待审批
     */
    public static final Integer AUDIT_STATE_TODO = 0;
    /**
     * 审批状态：2-审批未通过
     */
    public static final Integer AUDIT_STATE_REJECT = 2;

    /**
     * 审批状态：1-审批通过
     */
    public static final Integer AUDIT_STATE_AGREE = 1;

    /**
     * 状态-正常
     */
    public static final Integer STATUS_NORMAL = 0;

    /**
     * 状态-停用
     */
    public static final Integer STATUS_UNNORMAL = 1;
    /**
     * 评论类型-留言
     */
    public static final String COMMENT_TYPE_LIUYAN="liuyan";
    /**
     * 博客主题
     */
    public static final String KEY_BLOG_THEME="blog.theme";
    /**
     * 登录页面
     */
    public static final String KEY_LOGIN_PAGE="login.page";

    /**
     * 编辑器类型
     */
    public static final String KEY_EDITOR_TYPE="editor.type";

    /**
     * 编辑器类型-editormd
     */
    public static final String EDITOR_TYPE_EDITORMD="editormd";

    /**
     * 编辑器类型-ueditor
     */
    public static final String EDITOR_TYPE_UEDITOR="ueditor";

    /**
     * 后台首页页面
     */
    public static final String KEY_ADMIN_INDEX="admin.index.type";

    /**
     * 后台首页页面-index
     */
    public static final String ADMIN_INDEX_INDEX="index";
    /**
     * 后台首页页面-index_topMenu
     */
    public static final String ADMIN_INDEX_TOP_MENU="index_topMenu";

    /**
     * 博客标签
     */
    public static final String KEY_TAGS_BLOG="blogTab";

    /**
     * 系统标签
     */
    public static final String KEY_TAGS_SYSTEM="s";

    /**
     * 个人标签
     */
    public static final String KEY_TAGS_PERSON="p";

    /**
     * 文章模板标签
     */
    public static final String KEY_TAGS_ARTICLE_TEMPLATE="articleTemplate";
}
