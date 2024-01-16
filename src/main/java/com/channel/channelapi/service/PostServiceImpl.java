package com.channel.channelapi.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.channel.channelapi.exception.BaseException;
import com.channel.channelapi.model.Channel;
import com.channel.channelapi.model.Post;
import com.channel.channelapi.repository.ChannelRepository;
import com.channel.channelapi.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private ChannelRepository channelRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public Post createPost(Long channelId, Post e) throws BaseException {
        Channel channel = channelRepository.findById(channelId)
                .orElseThrow(() -> new BaseException("no channel found with that id : " + channelId));

        return postRepository.save(Post.builder().content(e.getContent()).channel(channel).build());
    }

    @Override
    public Post getPost(Long postId) throws BaseException {
        return postRepository.findById(postId)
                .orElseThrow(() -> new BaseException("no post found with that id : " + postId));
    }

    @Override
    public List<Post> getPostsByChannel(Long channelId) throws BaseException {
        return postRepository.findByChannelId(channelId);
    }

    @Override
    public Post updatePost(Long postId, Post e) throws BaseException {
        Optional<Post> optional = postRepository.findById(postId);

        if (optional.isEmpty())
            throw new BaseException("no post found with that id :" + postId);

        Post post = optional.get();

        if (Objects.nonNull(e.getContent()))
            post.setContent(e.getContent());

        return postRepository.save(post);
    }

    @Override
    public void deletePost(Long postId) throws BaseException {
        Optional<Post> optional = postRepository.findById(postId);

        if (optional.isEmpty())
            throw new BaseException("no post found with that id :" + postId);

        postRepository.deleteById(postId);
    }

    @Override
    public void deletePostsByChannel(Long channelId) throws BaseException {
        postRepository.deleteByChannelId(channelId);
    }

}
