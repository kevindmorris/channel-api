package com.channel.api.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.channel.api.dto.CommentDto;
import com.channel.api.dto.PostDto;
import com.channel.api.exception.PostNotFoundException;
import com.channel.api.model.Comment;
import com.channel.api.model.Post;
import com.channel.api.repository.CommentRepository;
import com.channel.api.repository.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    public Post create(PostDto postDto) {
        return postRepository.save(new Post(postDto.getTitle(), postDto.getBody()));
    }

    public Post createComment(Long id, CommentDto commentDto) {
        Post post = get(id);
        commentRepository.save(new Comment(commentDto.getBody(), post));
        return post;
    }

    public List<Post> getAll() {
        return postRepository.findAll();
    }

    public Post get(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
    }

    public Post update(Long id, PostDto postDto) {
        return postRepository.findById(id)
                .map(post -> {
                    if (Objects.nonNull(postDto.getTitle())) {
                        post.setTitle(postDto.getTitle());
                    }
                    if (Objects.nonNull(postDto.getBody())) {
                        post.setBody(postDto.getBody());
                    }
                    return postRepository.save(post);
                }).orElseThrow(() -> new PostNotFoundException(id));
    }

    public void delete(Long id) {
        postRepository.deleteById(id);
    }

}
