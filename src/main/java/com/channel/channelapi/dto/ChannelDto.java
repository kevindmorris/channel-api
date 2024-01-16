package com.channel.channelapi.dto;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

import com.channel.channelapi.model.Channel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(value = Include.NON_NULL)
@Getter
@Setter
public class ChannelDto {

    private Long id;
    private Instant createdDate;
    private Instant updatedDate;
    private String content;
    private Set<PostDto> posts;
    private Integer numPosts;

    public static ChannelDto toBasic(Channel channel) {
        ChannelDto dto = new ChannelDto();
        dto.setId(channel.getId());
        dto.setCreatedDate(channel.getCreatedDate());
        dto.setContent(channel.getContent());
        dto.setNumPosts(channel.getPosts().size());
        return dto;
    }

    public static ChannelDto toComplex(Channel channel) {
        ChannelDto dto = new ChannelDto();
        dto.setId(channel.getId());
        dto.setCreatedDate(channel.getCreatedDate());
        dto.setUpdatedDate(channel.getUpdatedDate());
        dto.setContent(channel.getContent());
        dto.setNumPosts(channel.getPosts().size());
        dto.setPosts(channel.getPosts().stream().map(PostDto::toBasic).collect(Collectors.toSet()));
        return dto;
    }

}
