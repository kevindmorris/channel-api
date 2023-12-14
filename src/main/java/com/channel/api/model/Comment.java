package com.channel.api.model;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table(name = "COMMENTS")
@Entity
@Getter
@Setter
public class Comment {

    @Column(nullable = false)
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @CreationTimestamp
    private Instant creationDate;

    @Column(nullable = false)
    private String body;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public Comment() {
    }

    public Comment(String body, Post post) {
        this.body = body;
        this.post = post;
    }

}
