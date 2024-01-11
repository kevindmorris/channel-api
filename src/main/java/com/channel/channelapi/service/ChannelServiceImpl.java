package com.channel.channelapi.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.channel.channelapi.dto.ChannelDto;
import com.channel.channelapi.exception.BaseException;
import com.channel.channelapi.model.Channel;
import com.channel.channelapi.repository.ChannelRepository;

@Service
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    private ChannelRepository channelRepository;

    @Override
    public Channel createChannel(ChannelDto e) throws BaseException {
        return channelRepository.save(new Channel(e.getContent()));
    }

    @Override
    public List<Channel> getChannels() throws BaseException {
        return channelRepository.findAll();
    }

    @Override
    public Channel getChannel(Long channelId) throws BaseException {
        return channelRepository.findById(channelId)
                .orElseThrow(() -> new BaseException("no channel found with that id : " + channelId));
    }

    @Override
    public Channel updateChannel(Long channelId, ChannelDto e) throws BaseException {
        Optional<Channel> optional = channelRepository.findById(channelId);

        if (optional.isEmpty())
            throw new BaseException("no channel found with that id :" + channelId);

        Channel channel = optional.get();

        if (Objects.nonNull(e.getContent()))
            channel.setContent(e.getContent());

        return channelRepository.save(channel);
    }

    @Override
    public void deleteChannel(Long channelId) throws BaseException {
        Optional<Channel> optional = channelRepository.findById(channelId);

        if (optional.isEmpty())
            throw new BaseException("no channel found with that id :" + channelId);

        channelRepository.deleteById(channelId);
    }

}
