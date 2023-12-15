package com.channel.channelapi.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.channel.channelapi.dto.CommentDto;
import com.channel.channelapi.dto.PostDto;
import com.channel.channelapi.service.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "2. Post Controller", description = "This controller exposes endpoints to manage posts.")
public class PostController {

    @Autowired
    PostService postService;

    @Operation(summary = "Create a comment.")
    @PostMapping("/posts/{id}/comments")
    public ResponseEntity<PostDto> createCommentInPost(@PathVariable Long id, @RequestBody CommentDto comment) {
        return ResponseEntity.ok(PostDto.toComplex(postService.createComment(id, comment)));
    }

    @Operation(summary = "Get a post.")
    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts().stream().map(PostDto::toBasic).collect(Collectors.toList()));
    }

    @Operation(summary = "Get a post.")
    @GetMapping("/posts/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable Long id) {
        return ResponseEntity.ok(PostDto.toComplex(postService.getPost(id)));
    }

    @Operation(summary = "Update a post.")
    @PutMapping("posts/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable Long id, @RequestBody PostDto post) {
        return ResponseEntity.ok(PostDto.toComplex(postService.updatePost(id, post)));
    }

    @Operation(summary = "Delete a post.")
    @DeleteMapping("posts/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }

}