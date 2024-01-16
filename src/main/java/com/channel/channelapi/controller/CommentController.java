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

import com.channel.channelapi.dto.CommentDto;
import com.channel.channelapi.exception.BaseException;
import com.channel.channelapi.model.Comment;
import com.channel.channelapi.service.CommentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@Tag(name = "3. Comment Controller", description = "This controller exposes endpoints to manage comment content.")
public class CommentController {

    @Autowired
    CommentService commentService;

    private static Logger logger = LoggerFactory.getLogger(CommentController.class);

    // POST
    @Operation(summary = "Create a comment.")
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(
            @PathVariable Long postId,
            @RequestBody Comment comment) throws BaseException {
        try {
            logger.info(String.format("POST /posts/%s/comments", postId));
            CommentDto res = CommentDto.toComplex(commentService.createComment(postId, comment));
            logger.info(String.format("comment CREATED id: %s", res.getId()));
            return ResponseEntity.ok(res);
        } catch (Exception ex) {
            logger.warn("Exception: " + ex.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    // GET
    @Operation(summary = "Get a comment.")
    @GetMapping("/comments/{commentId}")
    public ResponseEntity<CommentDto> getComment(
            @PathVariable Long commentId) throws BaseException {
        try {
            logger.info(String.format("GET /comments/%s", commentId));
            CommentDto res = CommentDto.toComplex(commentService.getComment(commentId));
            logger.info(String.format("comment RETRIEVED id: %s", res.getId()));
            return ResponseEntity.ok(res);
        } catch (Exception ex) {
            logger.warn("Exception: " + ex.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Get a posts's comments.")
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getPost(@PathVariable Long postId)
            throws BaseException {
        try {
            logger.info(String.format("GET /posts/%s/comments", postId));
            List<CommentDto> res = commentService.getCommentsByPost(postId).stream().map(CommentDto::toComplex)
                    .collect(Collectors.toList());
            logger.info(String.format("comments RETRIEVED"));
            return ResponseEntity
                    .ok(res);
        } catch (Exception ex) {
            logger.warn("Exception: " + ex.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    // PUT
    @Operation(summary = "Update a comment.")
    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(
            @PathVariable Long commentId, @RequestBody Comment comment) throws BaseException {
        try {
            logger.info(String.format("PUT /comments/%s", commentId));
            CommentDto res = CommentDto.toComplex(commentService.updateComment(commentId, comment));
            logger.info(String.format("comment UPDATED id: %s", res.getId()));
            return ResponseEntity.ok(res);
        } catch (Exception ex) {
            logger.warn("Exception: " + ex.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    // DELETE
    @Operation(summary = "Delete a comment.")
    @DeleteMapping("/comments/{commentId}")
    public void deleteComment(
            @PathVariable Long commentId) throws BaseException {
        try {
            logger.info(String.format("DELETE /comments/%s", commentId));
            commentService.deleteComment(commentId);
            logger.info(String.format("comment DELETED id: %s", commentId));
        } catch (Exception ex) {
            logger.warn("Exception: " + ex.getMessage());
        }
    }

    @Operation(summary = "Delete a posts's comments.")
    @DeleteMapping("/posts/{postId}/comments")
    public void deleteCommentsByPost(@PathVariable Long postId) throws BaseException {
        try {
            logger.info(String.format("DELETE /posts/%s/comments", postId));
            commentService.deleteCommentsByPost(postId);
            logger.info(String.format("comment DELETED id: %s", postId));
        } catch (Exception ex) {
            logger.warn("Exception: " + ex.getMessage());
        }

    }

}
