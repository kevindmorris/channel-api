package com.channel.channelapi.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.channel.channelapi.dto.PostDto;
import com.channel.channelapi.exception.BaseException;
import com.channel.channelapi.service.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@Tag(name = "2. Post Controller", description = "This controller exposes endpoints to manage post content.")
public class PostController {

    @Autowired
    PostService postService;

    // POST
    @Operation(summary = "Create a post.")
    @PostMapping("/channels/{channelId}/posts")
    public ResponseEntity<PostDto> createPost(
            @PathVariable Long channelId,
            @RequestBody PostDto post) throws BaseException {
        return ResponseEntity.ok(PostDto.toComplex(postService.createPost(channelId, post)));
    }

    // GET
    @Operation(summary = "Get a post.")
    @GetMapping("/channels/{channelId}/posts/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable Long channelId, @PathVariable Long postId)
            throws BaseException {
        return ResponseEntity.ok(PostDto.toComplex(postService.getPost(postId)));
    }

    @Operation(summary = "Get a channel's post.")
    @GetMapping("/channels/{channelId}/posts")
    public ResponseEntity<List<PostDto>> getPost(@PathVariable Long channelId)
            throws BaseException {
        return ResponseEntity.ok(
                postService.getPostsByChannel(channelId).stream().map(PostDto::toBasic).collect(Collectors.toList()));
    }

    // PUT
    @Operation(summary = "Update a post.")
    @PutMapping("/channels/{channelId}/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@PathVariable Long channelId, @PathVariable Long postId,
            @RequestBody PostDto post) throws BaseException {
        return ResponseEntity.ok(PostDto.toComplex(postService.updatePost(postId, post)));
    }

    // DELETE
    @Operation(summary = "Delete a post.")
    @DeleteMapping("/channels/{channelId}/posts/{postId}")
    public void deletePost(@PathVariable Long channelId, @PathVariable Long postId) throws BaseException {
        postService.deletePost(postId);
    }

}
