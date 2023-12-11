package com.channel.api.exception;

public class CommentNotFoundException extends RuntimeException {

    public CommentNotFoundException(String id) {
        super("Could not find post " + id);
    }

}
