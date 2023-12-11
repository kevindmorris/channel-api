package com.channel.api.model;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Post {

    private @Id @GeneratedValue(strategy = GenerationType.UUID) String id;
    private @CreationTimestamp Instant creationDate;
    private String content;

    public Post() {
    }

    public Post(String content) {
        this.content = content;
    }

}
