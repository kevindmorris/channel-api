package com.channel.channelapi.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.channel.channelapi.repository.ChannelRepository;

@Configuration
class DatabaseConfig {

    @Bean
    CommandLineRunner initDatabase(ChannelRepository channelRepository) {

        return args -> {

        };
    }
}
