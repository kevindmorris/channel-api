package com.channel.channelapi.dto;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

import com.channel.channelapi.model.Post;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(value = Include.NON_NULL)
@Getter
@Setter
public class PostDto {

    private Long id;
    private Instant createdDate;
    private Instant updatedDate;
    private String content;
    private ChannelDto channel;
    private Set<CommentDto> comments;
    private Integer numComments;

    public static PostDto toBasic(Post post) {
        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setCreatedDate(post.getCreatedDate());
        dto.setUpdatedDate(post.getUpdatedDate());
        dto.setContent(post.getContent());
        dto.setNumComments(post.getComments().size());
        return dto;
    }

    public static PostDto toComplex(Post post) {
        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setCreatedDate(post.getCreatedDate());
        dto.setUpdatedDate(post.getUpdatedDate());
        dto.setContent(post.getContent());
        dto.setChannel(ChannelDto.toBasic(post.getChannel()));
        dto.setComments(post.getComments().stream().map(CommentDto::toBasic).collect(Collectors.toSet()));
        return dto;
    }

}
