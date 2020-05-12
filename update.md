## 更新日志
## 2020年1月3日
1. 新增登录页主题solo

![输入图片说明](https://images.gitee.com/uploads/images/2020/0103/210857_230b6b85_528854.jpeg "solo.jpg")


```
INSERT INTO `cms_login_page` (`id`, `name`, `code`, `cover_img`, `create_time`, `create_by`) VALUES ('3', 'solo', 'solo', '/images/loginPage/solo.jpg', '2019-12-24 10:54:32', 'ry');

```

## 2020年1月1日
1. 新版文章模板列表页面（瀑布流滑动到底部加载）

![输入图片说明](https://images.gitee.com/uploads/images/2020/0101/123140_febbee5c_528854.jpeg "12.jpg")

## 2019年12月31日
1. 新增文章模板管理功能，该功能是为其它功能做基础数据维护的。所以后续会在该功能基础上作其它更新。
2. 更新：文章编辑页面封面图片从素材管理选择界面选择

![输入图片说明](https://images.gitee.com/uploads/images/2019/1231/142230_b4545b1b_528854.png "11.png")

菜单sql:

```
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('3100', '文章模板管理', '2025', '5', '/cms/articleTemplate', 'menuItem', 'C', '0', 'cms:articleTemplate:view', '#', 'admin', '2019-12-31 09:14:26', '', NULL, '');

```

数据字典sql：
```
INSERT INTO `sys_dict_type` (`dict_id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('115', '模板分类', 'template_type', '0', 'admin', '2019-11-17 12:33:38', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('177', '1', '公共', 'public', 'share_type', NULL, 'primary', 'Y', '0', 'admin', '2019-12-31 08:45:54', '', NULL, NULL);
INSERT INTO `sys_dict_data` (`dict_code`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('178', '2', '会员', 'vip', 'share_type', '', 'success', 'N', '0', 'admin', '2019-12-31 08:49:08', 'admin', '2019-12-31 11:53:30', '');

```
新建表结构：

```

DROP TABLE IF EXISTS `cms_article_template`;
CREATE TABLE `cms_article_template` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) DEFAULT NULL COMMENT '文章模板名称',
  `tags` varchar(255) DEFAULT NULL COMMENT '标签',
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户ID',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名称',
  `content` text COMMENT '内容',
  `share_type` varchar(50) DEFAULT NULL COMMENT '共享类型',
  `weight` int(11) DEFAULT NULL COMMENT '权重',
  `hot_falg` smallint(6) DEFAULT NULL COMMENT '最热',
  `new_flag` smallint(6) DEFAULT NULL COMMENT '最新',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `audit` smallint(6) DEFAULT NULL COMMENT '审核标志',
  `audit_time` datetime DEFAULT NULL COMMENT '审核时间',
  `audit_by` varchar(50) DEFAULT NULL COMMENT '审核人',
  `audit_name` varchar(50) DEFAULT NULL COMMENT '审核人名称',
  `audit_reason` varchar(255) DEFAULT NULL COMMENT '原因',
  PRIMARY KEY (`id`)
)
```
表数据有点多，没法贴出来，请进群下载！


## 2019年12月30日
1. admin模块新增系统事件功能


```
CREATE TABLE `sys_event_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户ID',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名称',
  `event_code` varchar(50) DEFAULT NULL COMMENT '事件代码',
  `event_name` varchar(50) DEFAULT NULL COMMENT '事件名称',
  `source` varchar(255) DEFAULT NULL COMMENT '来源',
  `datas` text COMMENT '参数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) 

INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('3099', '系统事件日志', '2', '6', '/system/eventLog', 'menuItem', 'C', '0', 'system:eventLog:view', '#', 'admin', '2019-12-30 20:40:31', '', NULL, '');

```

## 2019年12月29日
1. 后台首页页面可配置选择（可在sys_config表中配置admin.index.type项来指定编辑器可选项有index和index_topMenu）；index_topMenu页面是今天新增的一级菜单在顶部显示。

![输入图片说明](https://images.gitee.com/uploads/images/2019/1229/171832_04f9f4b7_528854.jpeg "2.jpg")

sys_config表:


```
INSERT INTO `sys_config` (`config_id`, `config_name`, `config_key`, `config_value`, `config_type`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('17', '后台首页', 'admin.index.type', 'index_topMenu', 'Y', 'admin', '2018-03-16 11:33:00', 'admin', '2019-10-10 13:41:12', '后台首页类型。可选的index/index_topMenu');
```

## 2019年12月28日
1. 文章管理增加markdown文本编辑器支持（可在sys_config表中配置editor.type项来指定编辑器可选项有editormd和ueditor）

![输入图片说明](https://images.gitee.com/uploads/images/2019/1228/140012_6a7f9677_528854.jpeg "markdown.jpg")

sys_config表:
```
INSERT INTO `sys_config` (`config_id`, `config_name`, `config_key`, `config_value`, `config_type`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('16', '编辑器类型', 'editor.type', 'editormd', 'Y', 'admin', '2018-03-16 11:33:00', 'admin', '2019-10-10 13:41:12', '默认为空 可选有editormd/ueditor');
```
## 2019年12月25日
1. 新增登陆页面配置功能
2. 新增登录页管理功能

![输入图片说明](https://images.gitee.com/uploads/images/2019/1225/133117_8a40e33f_528854.jpeg "process.jpg")
可在sys_config表中配置login.page项来指定登录页面

sys_config表:
INSERT INTO `sys_config` (`config_id`, `config_name`, `config_key`, `config_value`, `config_type`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('15', '登录页面', 'login.page', 'process', 'Y', 'admin', '2018-03-16 11:33:00', 'admin', '2019-10-10 13:41:12', '默认为空 可选有process/');

cms_login_page表:
CREATE TABLE `cms_login_page` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '登录主题名称',
  `code` varchar(50) DEFAULT NULL COMMENT '页面代码',
  `cover_img` varchar(255) DEFAULT NULL COMMENT '封面图片',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


INSERT INTO `cms_login_page` VALUES ('1', '默认', 'default', '/images/loginPage/default.jpg', '2019-12-24 10:54:32', 'ry');
INSERT INTO `cms_login_page` VALUES ('2', 'process', 'process', '/images/loginPage/process.jpg', '2019-12-24 10:54:32', 'ry');
## 2019年12月23日
1. 更新：文章新增和编辑页面增加栏目选择项

![输入图片说明](https://images.gitee.com/uploads/images/2019/1223/081455_947bcf30_528854.jpeg "1.jpg")
![输入图片说明](https://images.gitee.com/uploads/images/2019/1223/081510_cff21afd_528854.jpeg "2.jpg")

2. 完善ruoyi-oss模块功能
![输入图片说明](https://images.gitee.com/uploads/images/2019/1223/140718_717c64ba_528854.jpeg "1.jpg")

 **sys_config表sql:** 
INSERT INTO `sys_config` (`config_id`, `config_name`, `config_key`, `config_value`, `config_type`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('4', 'oss存储配置', 'sys.oss.cloudStorage', '{\"type\":1,\"qiniuDomain\":\"http://832s18.s3-cn-south-1.qiniucs.com\",\"qiniuPrefix\":\"upload\",\"qiniuAccessKey\":\"RmWBeMWWW5L_8hVtzqHjRBLnN1GrrldgxvL1SUdT\",\"qiniuSecretKey\":\"qFasmo516ADBxCKA2lwOzphx5FCXlYKa3GNXKuS6\",\"qiniuBucketName\":\"media\",\"aliyunDomain\":\"\",\"aliyunPrefix\":\"\",\"aliyunEndPoint\":\"\",\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qcloudBucketName\":\"\",\"qcloudRegion\":\"\"}', 'Y', 'admin', '2018-03-16 11:33:00', 'ry', '2019-10-10 13:41:12', 'oss存储配置(七牛，阿里，腾讯三选一)');
 **sys_menu表sql:** 
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('2005', 'OSS文件管理', '1', '10', '/system/oss', 'menuItem', 'C', '0', 'system:oss:view', '#', 'admin', '2018-11-16 13:59:45', 'admin', '2019-12-23 14:03:43', '');

## 2019年12月22日

1. 新增websocket模块
2. pblog博客主题新增在线人数展示
3. 后台新增消息群发页面

![输入图片说明](https://images.gitee.com/uploads/images/2019/1222/192113_c6f37838_528854.jpeg "1.jpg")
![输入图片说明](https://images.gitee.com/uploads/images/2019/1222/192202_43f42552_528854.jpeg "2.jpg")

菜单sql:

INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('3097', '消息群发', '3', '4', '/notice', 'menuItem', 'C', '0', 'notice', '#', 'admin', '2019-12-22 15:17:46', '', NULL, '');


## 2019年12月21日

1. 优化:新增切换博客主题模板功能。
2. 新增：博客主题管理功能。

菜单sql:
INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `parent_id`, `order_num`, `url`, `target`, `menu_type`, `visible`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('3096', '博客主题', '2025', '1', '/cms/blogTheme', 'menuItem', 'C', '0', 'cms:blogTheme:view', '#', 'admin', '2019-12-21 08:50:08', '', NULL, '');

CREATE TABLE `blog_theme` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '主题名称',
  `code` varchar(50) DEFAULT NULL COMMENT '主题代码',
  `cover_img` varchar(255) DEFAULT NULL COMMENT '封面图片',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

INSERT INTO `blog_theme` VALUES ('1', 'Avatar响应式博客模版主题', 'avatar', '/images/cover/1561132050443308.jpg', '2019-12-21 08:55:00', 'ry');
INSERT INTO `blog_theme` VALUES ('2', '葡萄资讯模板主题，葡萄内容管理系统模板主题', 'pnews', '/images/cover/201903051436106979.jpg', '2019-12-21 08:55:31', 'ry');
INSERT INTO `blog_theme` VALUES ('3', '“pblog”个性博客模版主题，PT-CMS模版主题，免费下载', 'pblog', '/images/cover/pblog.jpg', '2019-12-21 08:55:58', 'ry');
