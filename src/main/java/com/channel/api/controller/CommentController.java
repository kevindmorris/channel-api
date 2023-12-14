package com.channel.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.channel.api.dto.CommentDto;
import com.channel.api.service.CommentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Comment Controller", description = "This controller exposes endpoints to manage comments.")
public class CommentController {

    @Autowired
    CommentService commentService;

    @Operation(summary = "Get a comment.")
    @GetMapping("/comments/{id}")
    public ResponseEntity<CommentDto> getComment(@PathVariable Long id) {
        return ResponseEntity.ok(CommentDto.toComplex(commentService.get(id)));
    }

    @Operation(summary = "Update a comment.")
    @PutMapping("comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Long id, @RequestBody CommentDto comment) {
        return ResponseEntity.ok(CommentDto.toComplex(commentService.update(id, comment)));
    }

    @Operation(summary = "Delete a comment.")
    @DeleteMapping("comments/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.delete(id);
    }

}
