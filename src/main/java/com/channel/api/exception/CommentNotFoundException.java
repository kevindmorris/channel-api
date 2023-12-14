package com.channel.api.exception;

public class CommentNotFoundException extends RuntimeException {

    public CommentNotFoundException(Long id) {
        super("Could not find post " + id);
    }

}
