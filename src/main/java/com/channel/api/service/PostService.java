package com.channel.api.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.channel.api.dto.PostDto;
import com.channel.api.exception.PostNotFoundException;
import com.channel.api.model.Post;
import com.channel.api.repository.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post save(PostDto dto) {
        return postRepository.save(new Post(dto.getTitle(), dto.getBody()));
    }

    public List<Post> all() {
        return postRepository.findAll();
    }

    public Post get(String id) {
        return postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
    }

    public Post update(PostDto dto, String id) {
        return postRepository.findById(id)
                .map(post -> {
                    if (Objects.nonNull(dto.getTitle())) {
                        post.setTitle(dto.getTitle());
                    }
                    if (Objects.nonNull(dto.getBody())) {
                        post.setBody(dto.getBody());
                    }
                    return postRepository.save(post);
                }).orElseThrow(() -> new PostNotFoundException(id));
    }

    public void delete(String id) {
        postRepository.deleteById(id);
    }

}
