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

import com.channel.channelapi.dto.ChannelDto;
import com.channel.channelapi.dto.PostDto;
import com.channel.channelapi.service.ChannelService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "1. Channel Controller", description = "This controller exposes endpoints to manage channels.")
public class ChannelController {

    @Autowired
    ChannelService channelService;

    @Operation(summary = "Create a channel.")
    @PostMapping("/channels")
    public ResponseEntity<ChannelDto> createChannel(@RequestBody ChannelDto channel) {
        return ResponseEntity.ok(ChannelDto.toComplex(channelService.createChannel(channel)));
    }

    @Operation(summary = "Create a post.")
    @PostMapping("/channels/{id}/posts")
    public ResponseEntity<ChannelDto> createPostInChannel(@PathVariable Long id, @RequestBody PostDto post) {
        return ResponseEntity.ok(ChannelDto.toComplex(channelService.createPost(id, post)));
    }

    @Operation(summary = "Get all channels.")
    @GetMapping("/channels")
    public ResponseEntity<List<ChannelDto>> getChannels() {
        return ResponseEntity
                .ok(channelService.getAllChannels().stream().map(ChannelDto::toBasic).collect(Collectors.toList()));
    }

    @Operation(summary = "Get a channel.")
    @GetMapping("/channels/{id}")
    public ResponseEntity<ChannelDto> getChannel(@PathVariable Long id) {
        return ResponseEntity.ok(ChannelDto.toComplex(channelService.getChannel(id)));
    }

    @Operation(summary = "Update a channel.")
    @PutMapping("channels/{id}")
    public ResponseEntity<ChannelDto> updateChannel(@PathVariable Long id, @RequestBody ChannelDto channel) {
        return ResponseEntity.ok(ChannelDto.toComplex(channelService.updateChannel(id, channel)));
    }

    @Operation(summary = "Delete a channel.")
    @DeleteMapping("channels/{id}")
    public void deleteChannel(@PathVariable Long id) {
        channelService.deleteChannel(id);
    }

}
