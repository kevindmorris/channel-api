package com.channel.api.model;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table(name = "POSTS")
@Entity
@Getter
@Setter
public class Post {

    @Column(nullable = false)
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @CreationTimestamp
    private Instant creationDate;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String body;

    @OneToMany(mappedBy = "post")
    private Set<Comment> comments = new HashSet<>();

    public void addComment(Comment comment) {
        this.comments.add(comment);
        comment.setPost(this);
    }

    public Post() {
    }

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }

}
