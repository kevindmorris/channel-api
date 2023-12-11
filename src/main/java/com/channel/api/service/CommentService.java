package com.channel.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.channel.api.dto.CommentDto;
import com.channel.api.exception.CommentNotFoundException;
import com.channel.api.model.Comment;
import com.channel.api.repository.CommentRepository;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment save(CommentDto dto) {
        Comment comment = new Comment();
        comment.setBody(dto.getBody());
        return commentRepository.save(comment);
    }

    public List<Comment> all() {
        return commentRepository.findAll();
    }

    public Comment get(String id) {
        return commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException(id));
    }

    public Comment update(CommentDto dto, String id) {
        return commentRepository.findById(id)
                .map(comment -> {
                    comment.setBody(dto.getBody());
                    return commentRepository.save(comment);
                }).orElseThrow(() -> new CommentNotFoundException(id));
    }

    public void delete(String id) {
        commentRepository.deleteById(id);
    }

}
