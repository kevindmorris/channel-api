package com.channel.api.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.channel.api.model.Post;
import com.channel.api.repository.PostRepository;

@Configuration
class DatabaseConfig {

    private static final Logger log = LoggerFactory.getLogger(DatabaseConfig.class);

    @Bean
    CommandLineRunner initDatabase(PostRepository postRepository) {

        return args -> {
            log.info("Preloading "
                    + postRepository.save(new Post("Welcome to CHANNEL.", "This is the first preloaded post.")));
            log.info("Preloading "
                    + postRepository.save(new Post("Welcome to CHANNEL.", "This is the second preloaded post.")));
            log.info("Preloading "
                    + postRepository.save(new Post("Welcome to CHANNEL.", "This is the third preloaded post.")));

        };
    }
}
