package com.channel.channelapi.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.channel.channelapi.dto.CommentDto;
import com.channel.channelapi.exception.BaseException;
import com.channel.channelapi.model.Comment;
import com.channel.channelapi.model.Post;
import com.channel.channelapi.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostService postService;

    @Override
    public Comment createComment(Long postId, CommentDto e) throws BaseException {
        Post post = postService.getPost(postId);

        return commentRepository.save(new Comment(e.getContent(), post));
    }

    @Override
    public Comment getComment(Long commentId) throws BaseException {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new BaseException("no comment found with that id : " + commentId));
    }

    @Override
    public List<Comment> getCommentsByPost(Long postId) throws BaseException {
        return commentRepository.findByPostId(postId);
    }

    @Override
    public Comment updateComment(Long commentId, CommentDto e) throws BaseException {
        Optional<Comment> optional = commentRepository.findById(commentId);

        if (optional.isEmpty())
            throw new BaseException("no comment found with that id :" + commentId);

        Comment comment = optional.get();

        if (Objects.nonNull(e.getContent()))
            comment.setContent(e.getContent());

        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long commentId) throws BaseException {
        Optional<Comment> optional = commentRepository.findById(commentId);

        if (optional.isEmpty())
            throw new BaseException("no comment found with that id :" + commentId);

        commentRepository.deleteById(commentId);
    }

    @Override
    public void deleteCommentsByPost(Long postId) throws BaseException {
        commentRepository.deleteByPostId(postId);
    }

}
