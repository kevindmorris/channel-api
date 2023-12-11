package com.channel.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.channel.api.dto.PostDto;
import com.channel.api.exception.PostNotFoundException;
import com.channel.api.model.Post;
import com.channel.api.repository.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public Post save(PostDto post) {
        return repository.save(new Post(post.getContent()));
    }

    public List<Post> all() {
        return repository.findAll();
    }

    public Post get(String id) {
        return repository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
    }

    public Post update(PostDto post, String id) {
        return repository.findById(id)
                .map(p -> {
                    p.setContent(post.getContent());
                    return repository.save(p);
                })
                .orElseGet(() -> {
                    post.setId(id);
                    return repository.save(new Post(post.getContent()));
                });
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

}
