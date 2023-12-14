package com.channel.api.service;

import java.util.List;
import java.util.Objects;

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

    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    public Comment get(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException(id));
    }

    public Comment update(Long id, CommentDto commentDto) {
        return commentRepository.findById(id)
                .map(comment -> {
                    if (Objects.nonNull(commentDto.getBody())) {
                        comment.setBody(commentDto.getBody());
                    }
                    return commentRepository.save(comment);
                }).orElseThrow(() -> new CommentNotFoundException(id));
    }

    public void delete(Long id) {
        commentRepository.deleteById(id);
    }

}
