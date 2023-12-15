package com.channel.channelapi.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.channel.channelapi.model.Channel;
import com.channel.channelapi.repository.ChannelRepository;

@Configuration
class DatabaseConfig {

    private static final Logger log = LoggerFactory.getLogger(DatabaseConfig.class);

    @Bean
    CommandLineRunner initDatabase(ChannelRepository channelRepository) {

        return args -> {
            log.info("Preloading " + channelRepository.save(new Channel("Music")));
            log.info("Preloading " + channelRepository.save(new Channel("Movies")));
            log.info("Preloading " + channelRepository.save(new Channel("Sports")));

        };
    }
}
