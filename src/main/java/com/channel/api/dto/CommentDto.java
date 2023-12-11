package com.channel.api.dto;

import java.time.Instant;

import com.channel.api.model.Comment;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(value = Include.NON_NULL, content = Include.NON_EMPTY)
@Getter
@Setter
public class CommentDto {

    private String id;
    private Instant creationDate;
    private String body;
    private PostDto post;

    public static CommentDto toBasic(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setCreationDate(comment.getCreationDate());
        dto.setBody(comment.getBody());
        return dto;
    }

    public static CommentDto toComplex(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setCreationDate(comment.getCreationDate());
        dto.setBody(comment.getBody());
        dto.setPost(PostDto.toBasic(comment.getPost()));
        return dto;
    }

}
