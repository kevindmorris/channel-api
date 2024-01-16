package com.channel.channelapi.service;

import java.util.List;

import com.channel.channelapi.exception.BaseException;
import com.channel.channelapi.model.Channel;

public interface ChannelService {

    public Channel createChannel(Channel e) throws BaseException;

    public Channel getChannel(Long channelId) throws BaseException;

    public List<Channel> getChannels() throws BaseException;

    public Channel updateChannel(Long channelId, Channel e) throws BaseException;

    public void deleteChannel(Long channelId) throws BaseException;

    public void deleteChannels() throws BaseException;

}
