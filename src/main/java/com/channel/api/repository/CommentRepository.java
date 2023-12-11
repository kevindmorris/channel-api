package com.channel.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.channel.api.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, String> {

}
