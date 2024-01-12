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

import com.channel.channelapi.dto.ChannelDto;
import com.channel.channelapi.exception.BaseException;
import com.channel.channelapi.service.ChannelService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@Tag(name = "1. Channel Controller", description = "This controller exposes endpoints to manage channel content.")
public class ChannelController {

    @Autowired
    ChannelService channelService;

    private static Logger logger = LoggerFactory.getLogger(ChannelController.class);

    // POST
    @Operation(summary = "Create a channel.")
    @PostMapping("/channels")
    public ResponseEntity<ChannelDto> createChannel(@RequestBody ChannelDto channel) throws BaseException {
        try {
            logger.info("POST /channels");
            ChannelDto res = ChannelDto.toComplex(channelService.createChannel(channel));
            logger.info(String.format("channel CREATED id: %s", res.getId()));
            return ResponseEntity.ok(res);
        } catch (Exception ex) {
            logger.warn("Exception: " + ex.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    // GET
    @Operation(summary = "Get a channel.")
    @GetMapping("/channels/{channelId}")
    public ResponseEntity<ChannelDto> getChannel(@PathVariable Long channelId) throws BaseException {
        try {
            logger.info(String.format("GET /channels/%s", channelId));
            ChannelDto res = ChannelDto.toComplex(channelService.getChannel(channelId));
            logger.info(String.format("channel RETRIEVED id: %s", res.getId()));
            return ResponseEntity.ok(res);
        } catch (Exception ex) {
            logger.warn("Exception: " + ex.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Get all channels.")
    @GetMapping("/channels")
    public ResponseEntity<List<ChannelDto>> getChannels() throws BaseException {
        try {
            logger.info("GET /channels");
            List<ChannelDto> res = channelService.getChannels().stream().map(ChannelDto::toBasic)
                    .collect(Collectors.toList());
            logger.info(String.format("channels RETRIEVED"));
            return ResponseEntity
                    .ok(res);
        } catch (Exception ex) {
            logger.warn("Exception: " + ex.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    // PUT
    @Operation(summary = "Update a channel.")
    @PutMapping("channels/{channelId}")
    public ResponseEntity<ChannelDto> updateChannel(@PathVariable Long channelId, @RequestBody ChannelDto channel)
            throws BaseException {
        try {
            logger.info(String.format("PUT /channels/%s", channelId));
            ChannelDto res = ChannelDto.toComplex(channelService.updateChannel(channelId, channel));
            logger.info(String.format("channel UPDATED id: %s", res.getId()));
            return ResponseEntity.ok(res);
        } catch (Exception ex) {
            logger.warn("Exception: " + ex.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    // DELETE
    @Operation(summary = "Delete a channel.")
    @DeleteMapping("channels/{channelId}")
    public void deleteChannel(@PathVariable Long channelId) throws BaseException {
        try {
            logger.info(String.format("DELETE /channels/%s", channelId));
            channelService.deleteChannel(channelId);
            logger.info(String.format("channel DELETED id: %s", channelId));
        } catch (Exception ex) {
            logger.warn("Exception: " + ex.getMessage());
        }
    }

}
