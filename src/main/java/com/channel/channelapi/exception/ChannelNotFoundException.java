package com.channel.channelapi.exception;

public class ChannelNotFoundException extends RuntimeException {

    public ChannelNotFoundException(Long id) {
        super("Could not find channel " + id);
    }

}
