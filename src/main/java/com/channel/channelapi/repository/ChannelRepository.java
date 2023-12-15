package com.channel.channelapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.channel.channelapi.model.Channel;

public interface ChannelRepository extends JpaRepository<Channel, Long> {

}
