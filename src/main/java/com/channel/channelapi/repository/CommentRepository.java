package com.channel.channelapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.channel.channelapi.model.Comment;

import jakarta.transaction.Transactional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostId(Long postId);

    @Transactional
    void deleteByPostId(Long channelId);
    
}
