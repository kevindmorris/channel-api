package com.channel.channelapi.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.channel.channelapi.model.Channel;
import com.channel.channelapi.service.ChannelService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = ChannelController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class ChannelControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChannelService channelService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldCreateChannel() throws Exception {
        Channel channel = Channel.builder().content("Channel 1").build();
        when(channelService.createChannel(Mockito.any(Channel.class))).thenReturn(channel);

        mockMvc.perform(post("/channels").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(channel))).andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Channel 1"));
    }

    @Test
    public void shouldGetChannel() throws Exception {
        Channel channel = Channel.builder().content("Channel 1").build();
        when(channelService.getChannel(Mockito.anyLong())).thenReturn(channel);

        mockMvc.perform(get("/channels/{id}", 1L)).andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Channel 1"));
    }

    @Test
    public void shouldGetAllChannels() throws Exception {
        Channel channel = Channel.builder().content("Channel 1").build();
        Channel channel2 = Channel.builder().content("Channel 2").build();
        List<Channel> channels = List.of(channel, channel2);
        when(channelService.getChannels()).thenReturn(channels);

        mockMvc.perform(get("/channels")).andExpect(status().isOk()).andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void shouldUpdateChannel() throws Exception {
        Channel channel = Channel.builder().content("Updated Channel").build();
        when(channelService.updateChannel(Mockito.anyLong(), Mockito.any(Channel.class))).thenReturn(channel);

        mockMvc.perform(put("/channels/{id}", 1L).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(channel))).andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Updated Channel"));
    }

    @Test
    public void shouldDeleteChannel() throws Exception {
        doNothing().when(channelService).deleteChannel(Mockito.anyLong());

        mockMvc.perform(delete("/channels/{id}", 1L)).andExpect(status().isOk());
    }

}
