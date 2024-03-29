package com.channel.channelapi.model;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "CHANNELS")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Channel {

    @Column(nullable = false)
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @CreationTimestamp
    private Instant createdDate;

    @Column(nullable = false)
    @UpdateTimestamp
    private Instant updatedDate;

    @Column(nullable = false)
    private String content;

    @OneToMany(mappedBy = "channel", orphanRemoval = true)
    private final Set<Post> posts = new HashSet<>();

    public void addPost(Post post) {
        this.posts.add(post);
        post.setChannel(this);
    }

}
