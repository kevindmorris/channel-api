package com.channel.channelapi.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static Logger logger = LoggerFactory.getLogger(PostController.class);

    // POST
    @Operation(summary = "Create a post.")
    @PostMapping("/channels/{channelId}/posts")
    public ResponseEntity<PostDto> createPost(
            @PathVariable Long channelId,
            @RequestBody PostDto post) throws BaseException {
        try {
            logger.info(String.format("POST /channels/%s/posts", channelId));
            PostDto res = PostDto.toComplex(postService.createPost(channelId, post));
            logger.info(String.format("post CREATED id: %s", res.getId()));
            return ResponseEntity.ok(res);
        } catch (Exception ex) {
            logger.warn("Exception: " + ex.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    // GET
    @Operation(summary = "Get a post.")
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPost(
            @PathVariable Long postId) throws BaseException {
        try {
            logger.info(String.format("GET /posts/%s", postId));
            PostDto res = PostDto.toComplex(postService.getPost(postId));
            logger.info(String.format("post RETRIEVED id: %s", res.getId()));
            return ResponseEntity.ok(res);
        } catch (Exception ex) {
            logger.warn("Exception: " + ex.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Get a channels's posts.")
    @GetMapping("/channels/{channelId}/posts")
    public ResponseEntity<List<PostDto>> getChannel(@PathVariable Long channelId)
            throws BaseException {
        try {
            logger.info(String.format("GET /channels/%s/posts", channelId));
            List<PostDto> res = postService.getPostsByChannel(channelId).stream().map(PostDto::toComplex)
                    .collect(Collectors.toList());
            logger.info(String.format("posts RETRIEVED"));
            return ResponseEntity
                    .ok(res);
        } catch (Exception ex) {
            logger.warn("Exception: " + ex.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    // PUT
    @Operation(summary = "Update a post.")
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(
            @PathVariable Long postId, @RequestBody PostDto post) throws BaseException {
        try {
            logger.info(String.format("PUT /posts/%s", postId));
            PostDto res = PostDto.toComplex(postService.updatePost(postId, post));
            logger.info(String.format("post UPDATED id: %s", res.getId()));
            return ResponseEntity.ok(res);
        } catch (Exception ex) {
            logger.warn("Exception: " + ex.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    // DELETE
    @Operation(summary = "Delete a post.")
    @DeleteMapping("/posts/{postId}")
    public void deletePost(
            @PathVariable Long postId) throws BaseException {
        try {
            logger.info(String.format("DELETE /posts/%s", postId));
            postService.deletePost(postId);
            logger.info(String.format("post DELETED id: %s", postId));
        } catch (Exception ex) {
            logger.warn("Exception: " + ex.getMessage());
        }
    }

    @Operation(summary = "Delete a channels's posts.")
    @DeleteMapping("/channels/{channelId}/posts")
    public void deletePostsByChannel(@PathVariable Long channelId) throws BaseException {
        try {
            logger.info(String.format("DELETE /channels/%s/posts", channelId));
            postService.deletePostsByChannel(channelId);
            logger.info(String.format("posts DELETED id: %s", channelId));
        } catch (Exception ex) {
            logger.warn("Exception: " + ex.getMessage());
        }

    }

}
