package com.channel.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.channel.api.model.Post;

public interface PostRepository extends JpaRepository<Post, String> {

}
