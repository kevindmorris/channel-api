package com.channel.channelapi.repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.channel.channelapi.model.Channel;
import com.channel.channelapi.model.Post;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PostRepositoryTests {

    @Autowired
    private ChannelRepository channelRepository;
    @Autowired
    private PostRepository postRepository;

    @Test
    public void shouldCreatePost() {
                Channel channel = Channel.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                                .content("Channel 1").build();
        Channel savedChannel = channelRepository.save(channel);
        Post post = Post.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                .content("Post 1").channel(savedChannel).build();

        Post savedPost = postRepository.save(post);

        Assertions.assertThat(savedPost).isNotNull();
        Assertions.assertThat(savedPost.getId()).isNotNull();
        Assertions.assertThat(savedPost.getContent()).isEqualTo("Post 1");
    }

    @Test
    public void shouldGetPost() {
        Channel channel = Channel.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                        .content("Channel 1").build();
        Channel savedChannel = channelRepository.save(channel);
        Post post = Post.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                .content("Post 1").channel(savedChannel).build();

        Post savedPost = postRepository.save(post);
        Post retrievedPost = postRepository.findById(savedPost.getId()).get();

        Assertions.assertThat(retrievedPost).isNotNull();
        Assertions.assertThat(retrievedPost.getId()).isNotNull();
        Assertions.assertThat(retrievedPost.getContent()).isEqualTo("Post 1");
    }

    @Test
    public void shouldGetPostsByChannel() {
        Channel channel = Channel.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                        .content("Channel 1").build();
        Channel savedChannel = channelRepository.save(channel);
        Post post = Post.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                .content("Post 1").channel(savedChannel).build();
        Post post2 = Post.builder().id(2L).createdDate(Instant.now()).updatedDate(Instant.now())
                .content("Post 2").channel(savedChannel).build();

        postRepository.save(post);
        postRepository.save(post2);
        List<Post> retrievedPosts = postRepository.findByChannelId(savedChannel.getId());

        Assertions.assertThat(retrievedPosts).isNotNull();
        Assertions.assertThat(retrievedPosts.size()).isEqualTo(2);
    }

    @Test
    public void shouldUpdatePost() {
        Channel channel = Channel.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                        .content("Channel 1").build();
        Channel savedChannel = channelRepository.save(channel);
        Post post = Post.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                .content("Post 1").channel(savedChannel).build();

        Post savedPost = postRepository.save(post);
        savedPost.setContent("Updated Post");
        Post updatedPost = postRepository.save(savedPost);

        Assertions.assertThat(updatedPost).isNotNull();
        Assertions.assertThat(updatedPost.getId()).isNotNull();
        Assertions.assertThat(updatedPost.getContent()).isEqualTo("Updated Post");
    }

    @Test
    public void shouldDeletePost() {
        Channel channel = Channel.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                        .content("Channel 1").build();
        Channel savedChannel = channelRepository.save(channel);
        Post post = Post.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                .content("Post 1").channel(savedChannel).build();

        postRepository.save(post);
        postRepository.deleteById(post.getId());
        Optional<Post> deletedPost = postRepository.findById(post.getId());

        Assertions.assertThat(deletedPost).isEmpty();
    }

    @Test
    public void shouldDeletePostsByChannel() {
        Channel channel = Channel.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                        .content("Channel 1").build();
        Channel savedChannel = channelRepository.save(channel);
        Post post = Post.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                .content("Post 1").channel(savedChannel).build();
        Post post2 = Post.builder().id(2L).createdDate(Instant.now()).updatedDate(Instant.now())
                .content("Post 2").channel(savedChannel).build();

        postRepository.save(post);
        postRepository.save(post2);
        postRepository.deleteByChannelId(savedChannel.getId());
        List<Post> retrievedPosts = postRepository.findByChannelId(savedChannel.getId());

        Assertions.assertThat(retrievedPosts).isNotNull();
        Assertions.assertThat(retrievedPosts.size()).isEqualTo(0);
    }

}
