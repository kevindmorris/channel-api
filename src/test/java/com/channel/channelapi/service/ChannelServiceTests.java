package com.channel.channelapi.service;

import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.channel.channelapi.exception.BaseException;
import com.channel.channelapi.model.Channel;
import com.channel.channelapi.repository.ChannelRepository;

@ExtendWith(MockitoExtension.class)
public class ChannelServiceTests {

    @Mock
    private ChannelRepository channelRepository;

    @InjectMocks
    private ChannelServiceImpl channelService;

    @Test
    public void shouldCreateChannel() throws BaseException {
        Channel channel = Channel.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                .content("Channel 1").build();

        when(channelRepository.save(Mockito.any(Channel.class))).thenReturn(channel);

        Channel savedChannel = channelService.createChannel(channel);

        Assertions.assertThat(savedChannel).isNotNull();
        Assertions.assertThat(savedChannel.getContent()).isEqualTo("Channel 1");
    }

    @Test
    public void shouldGetChannel() throws BaseException {
        Channel channel = Channel.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                .content("Channel 1").build();

        when(channelRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(channel));

        Channel retrivedChannel = channelService.getChannel(1L);

        Assertions.assertThat(retrivedChannel).isNotNull();
        Assertions.assertThat(retrivedChannel.getContent()).isEqualTo("Channel 1");
    }

    @Test
    public void shouldGetAllChannels() throws BaseException {
        Channel channel = Channel.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                .content("Channel 1").build();
        Channel channel2 = Channel.builder().id(2L).createdDate(Instant.now()).updatedDate(Instant.now())
                .content("Channel 2").build();
        List<Channel> channels = List.of(channel, channel2);

        when(channelRepository.findAll()).thenReturn(channels);

        List<Channel> retrivedChannels = channelService.getChannels();

        Assertions.assertThat(retrivedChannels).isNotNull();
        Assertions.assertThat(retrivedChannels.size()).isEqualTo(2);
    }

    @Test
    public void shouldUpdateChannel() throws BaseException {
        Channel channel = Channel.builder().content("Updated Channel").build();

        when(channelRepository.save(Mockito.any(Channel.class))).thenReturn(channel);
        when(channelRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(channel));

        Channel updatedChannel = channelService.updateChannel(1L, channel);

        Assertions.assertThat(updatedChannel).isNotNull();
        Assertions.assertThat(updatedChannel.getContent()).isEqualTo("Updated Channel");
    }

    @Test
    public void shouldDeleteChannel() throws BaseException {
        Channel channel = Channel.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                .content("Channel 1").build();

        when(channelRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(channel));

        channelService.deleteChannel(1L);

        Mockito.verify(channelRepository).deleteById(1L);
    }

    @Test
    public void shouldDeleteChannels() throws BaseException {
        channelService.deleteChannels();
        
        Mockito.verify(channelRepository).deleteAll();
    }

}
