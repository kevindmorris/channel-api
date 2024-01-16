package com.channel.channelapi.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.channel.channelapi.exception.BaseException;
import com.channel.channelapi.model.Comment;
import com.channel.channelapi.model.Post;
import com.channel.channelapi.repository.CommentRepository;
import com.channel.channelapi.repository.PostRepository;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment createComment(Long postId, Comment e) throws BaseException {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BaseException("no post found with that id : " + postId));

        return commentRepository.save(Comment.builder().content(e.getContent()).post(post).build());
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
    public Comment updateComment(Long commentId, Comment e) throws BaseException {
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
