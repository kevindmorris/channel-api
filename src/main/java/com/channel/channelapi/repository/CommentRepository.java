package com.channel.channelapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.channel.channelapi.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    
}
