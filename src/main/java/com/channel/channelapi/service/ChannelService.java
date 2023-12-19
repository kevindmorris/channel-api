package com.channel.channelapi.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.channel.channelapi.dto.ChannelDto;
import com.channel.channelapi.exception.ChannelNotFoundException;
import com.channel.channelapi.model.Channel;
import com.channel.channelapi.repository.ChannelRepository;

@Service
public class ChannelService {

    @Autowired
    private ChannelRepository channelRepository;

    public Channel createChannel(ChannelDto channel) {
        return channelRepository.save(new Channel(channel.getTitle()));
    }

    public List<Channel> getAllChannels() {
        return channelRepository.findAll();
    }

    public Channel getChannel(Long id) {
        return channelRepository.findById(id).orElseThrow(() -> new ChannelNotFoundException(id));
    }

    public Channel updateChannel(Long id, ChannelDto newChannel) {
        return channelRepository.findById(id)
                .map(channel -> {
                    if (Objects.nonNull(newChannel.getTitle())) {
                        channel.setTitle(newChannel.getTitle());
                    }
                    return channelRepository.save(channel);
                }).orElseThrow(() -> new ChannelNotFoundException(id));
    }

    public void deleteChannel(Long id) {
        channelRepository.deleteById(id);
    }

}
