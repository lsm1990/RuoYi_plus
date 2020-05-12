package com.ruoyi.cms.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 评论对象 cms_comment
 * 
 * @author wujiyue
 * @date 2019-11-19
 */
public class Comment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 父ID */
    private Long pid;

    /** 目标ID */
    @Excel(name = "目标ID")
    private String tid;

    /** 呸数 */
    private Integer numPei;

    /** 喷子数 */
    private Integer numPenzi;

    /** 逗个数 */
    private Integer numDou;

    /** 给力数 */
    private Integer numGeili;

    /** 评论类型 */
    @Excel(name = "评论类型")
    private String type;

    /** 用户id */
    private String userId;

    /** 用户名称 */
    @Excel(name = "用户名称")
    private String userName;

    /** 用户头像 */
    @Excel(name = "用户头像")
    private String avatar;

    /** 评论内容 */
    private String content;

    /** 点赞数 */
    @Excel(name = "点赞数")
    private Long upVote;

    /** 反对数 */
    private Long downVote;

    /** QQ */
    @Excel(name = "QQ")
    private String qq;

    /** 邮箱 */
    private String email;

    /** IP */
    @Excel(name = "IP")
    private String ip;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 状态0正常 */
    @Excel(name = "状态0正常")
    private Integer status;

    private Comment parent;//扩展字段

    private Article article;//扩展字段

    public Comment getParent() {
        return parent;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setPid(Long pid) 
    {
        this.pid = pid;
    }

    public Long getPid() 
    {
        return pid;
    }
    public void setTid(String tid) 
    {
        this.tid = tid;
    }

    public String getTid() 
    {
        return tid;
    }
    public void setNumPei(Integer numPei) 
    {
        this.numPei = numPei;
    }

    public Integer getNumPei() 
    {
        return numPei;
    }
    public void setNumPenzi(Integer numPenzi) 
    {
        this.numPenzi = numPenzi;
    }

    public Integer getNumPenzi() 
    {
        return numPenzi;
    }
    public void setNumDou(Integer numDou) 
    {
        this.numDou = numDou;
    }

    public Integer getNumDou() 
    {
        return numDou;
    }
    public void setNumGeili(Integer numGeili) 
    {
        this.numGeili = numGeili;
    }

    public Integer getNumGeili() 
    {
        return numGeili;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setAvatar(String avatar) 
    {
        this.avatar = avatar;
    }

    public String getAvatar() 
    {
        return avatar;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setUpVote(Long upVote) 
    {
        this.upVote = upVote;
    }

    public Long getUpVote() 
    {
        return upVote;
    }
    public void setDownVote(Long downVote) 
    {
        this.downVote = downVote;
    }

    public Long getDownVote() 
    {
        return downVote;
    }
    public void setQq(String qq) 
    {
        this.qq = qq;
    }

    public String getQq() 
    {
        return qq;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setIp(String ip) 
    {
        this.ip = ip;
    }

    public String getIp() 
    {
        return ip;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("pid", getPid())
            .append("tid", getTid())
            .append("numPei", getNumPei())
            .append("numPenzi", getNumPenzi())
            .append("numDou", getNumDou())
            .append("numGeili", getNumGeili())
            .append("type", getType())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("avatar", getAvatar())
            .append("content", getContent())
            .append("upVote", getUpVote())
            .append("downVote", getDownVote())
            .append("qq", getQq())
            .append("email", getEmail())
            .append("ip", getIp())
            .append("address", getAddress())
            .append("createTime", getCreateTime())
            .append("status", getStatus())
            .toString();
    }
}
