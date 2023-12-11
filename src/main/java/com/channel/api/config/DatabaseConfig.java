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
    CommandLineRunner initDatabase(PostRepository repository) {

        return args -> {
            log.info("Preloading "
                    + repository.save(new Post("Welcome to CHANNEL.", "This is an initial preloaded post.")));
        };
    }
}
