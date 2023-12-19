package com.channel.channelapi.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.channel.channelapi.dto.PostDto;
import com.channel.channelapi.exception.PostNotFoundException;
import com.channel.channelapi.model.Channel;
import com.channel.channelapi.model.Post;
import com.channel.channelapi.repository.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ChannelService channelService;

    public Post createPost(Long id, PostDto post) {
        Channel channel = channelService.getChannel(id);
        return postRepository.save(new Post(post.getContent(), channel));
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
