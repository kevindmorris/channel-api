package com.channel.api.exception;

public class PostNotFoundException extends RuntimeException {

    public PostNotFoundException(String id) {
        super("Could not find post " + id);
    }

}
