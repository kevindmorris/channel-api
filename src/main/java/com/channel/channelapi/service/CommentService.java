package com.channel.channelapi.service;

import java.util.List;

import com.channel.channelapi.dto.CommentDto;
import com.channel.channelapi.exception.BaseException;
import com.channel.channelapi.model.Comment;

public interface CommentService {

    public Comment createComment(Long postId, CommentDto e) throws BaseException;

    public Comment getComment(Long commentId) throws BaseException;

    public List<Comment> getCommentsByPost(Long postId) throws BaseException;

    public Comment updateComment(Long commentId, CommentDto e) throws BaseException;

    public void deleteComment(Long commentId) throws BaseException;

    public void deleteCommentsByPost(Long channelId) throws BaseException;

}
