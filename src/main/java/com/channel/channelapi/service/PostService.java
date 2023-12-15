package com.channel.channelapi.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.channel.channelapi.dto.CommentDto;
import com.channel.channelapi.dto.PostDto;
import com.channel.channelapi.exception.PostNotFoundException;
import com.channel.channelapi.model.Comment;
import com.channel.channelapi.model.Post;
import com.channel.channelapi.repository.CommentRepository;
import com.channel.channelapi.repository.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    public Post createComment(Long id, CommentDto comment) {
        Post post = getPost(id);
        commentRepository.save(new Comment(comment.getContent(), post));
        return post;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPost(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
    }

    public Post updatePost(Long id, PostDto newPost) {
        return postRepository.findById(id)
                .map(post -> {
                    if (Objects.nonNull(newPost.getContent())) {
                        post.setContent(newPost.getContent());
                    }
                    return postRepository.save(post);
                }).orElseThrow(() -> new PostNotFoundException(id));
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

}
