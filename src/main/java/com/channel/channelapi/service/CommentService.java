package com.channel.channelapi.service;

import java.util.List;

import com.channel.channelapi.exception.BaseException;
import com.channel.channelapi.model.Comment;

public interface CommentService {

    public Comment createComment(Long postId, Comment e) throws BaseException;

    public Comment getComment(Long commentId) throws BaseException;

    public List<Comment> getCommentsByPost(Long postId) throws BaseException;

    public Comment updateComment(Long commentId, Comment e) throws BaseException;

    public void deleteComment(Long commentId) throws BaseException;

    public void deleteCommentsByPost(Long channelId) throws BaseException;

}
