package com.channel.api.dto;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

import com.channel.api.model.Post;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(value = Include.NON_EMPTY, content = Include.NON_EMPTY)
@Getter
@Setter
public class PostDto {

    private String id;
    private Instant creationDate;
    private String title;
    private String body;
    private Set<CommentDto> comments;

    public static PostDto toBasic(Post post) {
        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setCreationDate(post.getCreationDate());
        dto.setTitle(post.getTitle());
        dto.setBody(post.getBody());
        return dto;
    }

    public static PostDto toComplex(Post post) {
        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setCreationDate(post.getCreationDate());
        dto.setTitle(post.getTitle());
        dto.setBody(post.getBody());
        dto.setComments(post.getComments().stream().map(CommentDto::toBasic).collect(Collectors.toSet()));
        return dto;
    }

}
