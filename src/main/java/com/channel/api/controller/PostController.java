package com.channel.api.controller;

import org.springframework.web.bind.annotation.RestController;

import com.channel.api.dto.PostDto;
import com.channel.api.service.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@Tag(name = "Post Controller", description = "This controller exposes endpoints to manage posts.")
public class PostController {

    @Autowired
    PostService service;

    @Operation(summary = "Get all posts.")
    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getPosts() {
        return ResponseEntity.ok(service.all().stream().map(PostDto::from).collect(Collectors.toList()));
    }

    @Operation(summary = "Get a post.")
    @GetMapping("/posts/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable String id) {
        return ResponseEntity.ok(PostDto.from(service.get(id)));
    }

    @Operation(summary = "Create a post.")
    @PostMapping("/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto post) {
        return ResponseEntity.ok(PostDto.from(service.save(post)));
    }

    @Operation(summary = "Update a post.")
    @PutMapping("posts/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable String id, @RequestBody PostDto post) {
        return ResponseEntity.ok(PostDto.from(service.update(post, id)));
    }

    @Operation(summary = "Delete a post.")
    @DeleteMapping("posts/{id}")
    public void deletePost(@PathVariable String id) {
        service.delete(id);
    }

}
