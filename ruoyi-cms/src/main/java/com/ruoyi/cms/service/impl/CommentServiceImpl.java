package com.ruoyi.cms.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.cms.mapper.CommentMapper;
import com.ruoyi.cms.domain.Comment;
import com.ruoyi.cms.service.ICommentService;
import com.ruoyi.common.core.text.Convert;

/**
 * 评论Service业务层处理
 * 
 * @author wujiyue
 * @date 2019-11-19
 */
@Service
public class CommentServiceImpl implements ICommentService 
{
    @Autowired
    private CommentMapper commentMapper;

    /**
     * 查询评论
     * 
     * @param id 评论ID
     * @return 评论
     */
    @Override
    public Comment selectCommentById(Long id)
    {
        return commentMapper.selectCommentById(id);
    }

    /**
     * 查询评论列表
     * 
     * @param comment 评论
     * @return 评论
     */
    @Override
    public List<Comment> selectCommentList(Comment comment)
    {
        return commentMapper.selectCommentList(comment);
    }

    @Override
    public List<Comment> selectComments(Comment comment) {
        return commentMapper.selectComments(comment);
    }

    /**
     * 新增评论
     * 
     * @param comment 评论
     * @return 结果
     */
    @Override
    public int insertComment(Comment comment)
    {
        comment.setCreateTime(DateUtils.getNowDate());
        return commentMapper.insertComment(comment);
    }

    /**
     * 修改评论
     * 
     * @param comment 评论
     * @return 结果
     */
    @Override
    public int updateComment(Comment comment)
    {
        comment.setUpdateTime(DateUtils.getNowDate());
        return commentMapper.updateComment(comment);
    }

    /**
     * 删除评论对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCommentByIds(String ids)
    {
        return commentMapper.deleteCommentByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除评论信息
     * 
     * @param id 评论ID
     * @return 结果
     */
    @Override
    public int deleteCommentById(Long id)
    {
        return commentMapper.deleteCommentById(id);
    }

    @Override
    public int upVote(String id) {
        return commentMapper.upVote(id);
    }
}
