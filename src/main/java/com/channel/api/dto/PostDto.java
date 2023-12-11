package com.channel.api.dto;

import java.time.Instant;

import com.channel.api.model.Post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {

    private String id;
    private Instant creationDate;
    private String content;

    public static PostDto from(Post post) {
        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setCreationDate(post.getCreationDate());
        dto.setContent(post.getContent());
        return dto;
    }

}
