package com.channel.channelapi.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.channel.channelapi.dto.CommentDto;
import com.channel.channelapi.exception.CommentNotFoundException;
import com.channel.channelapi.model.Comment;
import com.channel.channelapi.repository.CommentRepository;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment getComment(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException(id));
    }

    public Comment updateComment(Long id, CommentDto newComment) {
        return commentRepository.findById(id)
                .map(comment -> {
                    if (Objects.nonNull(newComment.getContent())) {
                        comment.setContent(newComment.getContent());
                    }
                    return commentRepository.save(comment);
                }).orElseThrow(() -> new CommentNotFoundException(id));
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

}
