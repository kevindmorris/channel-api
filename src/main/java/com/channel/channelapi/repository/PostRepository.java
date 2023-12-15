package com.channel.channelapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.channel.channelapi.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
