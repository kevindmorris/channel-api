package com.channel.channelapi.service;

import java.util.List;

import com.channel.channelapi.dto.PostDto;
import com.channel.channelapi.exception.BaseException;
import com.channel.channelapi.model.Post;

public interface PostService {

    public Post createPost(Long channelId, PostDto e) throws BaseException;

    public Post getPost(Long postId) throws BaseException;

    public List<Post> getPostsByChannel(Long channelId) throws BaseException;

    public Post updatePost(Long postId, PostDto e) throws BaseException;

    public void deletePost(Long postId) throws BaseException;

}
