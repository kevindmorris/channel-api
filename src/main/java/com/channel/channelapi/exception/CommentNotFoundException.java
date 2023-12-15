package com.channel.channelapi.exception;

public class CommentNotFoundException extends RuntimeException {

    public CommentNotFoundException(Long id) {
        super("Could not find comment " + id);
    }

}
