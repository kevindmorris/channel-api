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

import com.channel.channelapi.dto.CommentDto;
import com.channel.channelapi.exception.BaseException;
import com.channel.channelapi.service.CommentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@Tag(name = "3. Comment Controller", description = "This controller exposes endpoints to manage comment content.")
public class CommentController {

    @Autowired
    CommentService commentService;

    // POST
    @Operation(summary = "Create a comment.")
    @PostMapping("/channels/{channelId}/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(
            @PathVariable Long channelId,
            @PathVariable Long postId,
            @RequestBody CommentDto comment) throws BaseException {
        return ResponseEntity.ok(CommentDto.toComplex(commentService.createComment(postId, comment)));
    }

    // GET
    @Operation(summary = "Get a comment.")
    @GetMapping("/channels/{channelId}/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getComment(@PathVariable Long channelId, @PathVariable Long postId,
            @PathVariable Long commentId) throws BaseException {
        return ResponseEntity.ok(CommentDto.toComplex(commentService.getComment(commentId)));
    }

    @Operation(summary = "Get a posts's comments.")
    @GetMapping("/channels/{channelId}/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getPost(@PathVariable Long channelId, @PathVariable Long postId)
            throws BaseException {
        return ResponseEntity.ok(
                commentService.getCommentsByPost(postId).stream().map(CommentDto::toComplex)
                        .collect(Collectors.toList()));
    }

    // PUT
    @Operation(summary = "Update a comment.")
    @PutMapping("/channels/{channelId}/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Long channelId, @PathVariable Long postId,
            @PathVariable Long commentId, @RequestBody CommentDto comment) throws BaseException {
        return ResponseEntity.ok(CommentDto.toComplex(commentService.updateComment(commentId, comment)));
    }

    // DELETE
    @Operation(summary = "Delete a comment.")
    @DeleteMapping("/channels/{channelId}/posts/{postId}/comments/{commentId}")
    public void deleteComment(@PathVariable Long channelId, @PathVariable Long postId,
            @PathVariable Long commentId) throws BaseException {
        commentService.deleteComment(commentId);
    }

}
