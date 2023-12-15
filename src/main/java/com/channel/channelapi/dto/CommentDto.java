package com.channel.channelapi.dto;

import java.time.Instant;

import com.channel.channelapi.model.Comment;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(value = Include.NON_NULL)
@Getter
@Setter
public class CommentDto {

    private Long id;
    private Instant createdDate;
    private Instant updatedDate;
    private String content;
    private PostDto post;

    public static CommentDto toBasic(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setCreatedDate(comment.getCreatedDate());
        dto.setUpdatedDate(comment.getUpdatedDate());
        dto.setContent(comment.getContent());
        return dto;
    }

    public static CommentDto toComplex(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setCreatedDate(comment.getCreatedDate());
        dto.setUpdatedDate(comment.getUpdatedDate());
        dto.setContent(comment.getContent());
        dto.setPost(PostDto.toBasic(comment.getPost()));
        return dto;
    }

}
