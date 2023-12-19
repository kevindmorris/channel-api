package com.channel.channelapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.channel.channelapi.dto.CommentDto;
import com.channel.channelapi.service.CommentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@Tag(name = "3. Comment Controller", description = "This controller exposes endpoints to manage comments.")
public class CommentController {

    @Autowired
    CommentService commentService;

    @Operation(summary = "Create a comment.")
    @PostMapping("/comments")
    public ResponseEntity<CommentDto> createPost(
            @Parameter(description = "Post ID for the new comment.", required = true) @RequestParam Long id,
            @RequestBody CommentDto comment) {
        return ResponseEntity.ok(CommentDto.toComplex(commentService.createComment(id, comment)));
    }

    @Operation(summary = "Get a comment.")
    @GetMapping("/comments/{id}")
    public ResponseEntity<CommentDto> getPost(@PathVariable Long id) {
        return ResponseEntity.ok(CommentDto.toComplex(commentService.getComment(id)));
    }

    @Operation(summary = "Update a comment.")
    @PutMapping("comments/{id}")
    public ResponseEntity<CommentDto> updatePost(@PathVariable Long id, @RequestBody CommentDto comment) {
        return ResponseEntity.ok(CommentDto.toComplex(commentService.updateComment(id, comment)));
    }

    @Operation(summary = "Delete a comment.")
    @DeleteMapping("comments/{id}")
    public void deletePost(@PathVariable Long id) {
        commentService.deleteComment(id);
    }

}
