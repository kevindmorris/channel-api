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
import com.channel.channelapi.model.Comment;
import com.channel.channelapi.model.Post;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CommentRepositoryTests {

        @Autowired
        private ChannelRepository channelRepository;
        @Autowired
        private PostRepository postRepository;
        @Autowired
        private CommentRepository commentRepository;

        @Test
        public void shouldCreateComment() {
                Channel channel = Channel.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                                .content("Channel 1").build();
                Channel savedChannel = channelRepository.save(channel);
                Post post = Post.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                                .content("Post 1").channel(savedChannel).build();
                Post savedPost = postRepository.save(post);
                Comment comment = Comment.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                                .content("Comment 1").post(savedPost).build();

                Comment savedComment = commentRepository.save(comment);

                Assertions.assertThat(savedComment).isNotNull();
                Assertions.assertThat(savedComment.getId()).isNotNull();
                Assertions.assertThat(savedComment.getContent()).isEqualTo("Comment 1");
        }

        @Test
        public void shouldGetComment() {
                Channel channel = Channel.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                                .content("Channel 1").build();
                Channel savedChannel = channelRepository.save(channel);
                Post post = Post.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                                .content("Post 1").channel(savedChannel).build();
                Post savedPost = postRepository.save(post);
                Comment comment = Comment.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                                .content("Comment 1").post(savedPost).build();

                Comment savedComment = commentRepository.save(comment);
                Comment retrievedComment = commentRepository.findById(savedComment.getId()).get();

                Assertions.assertThat(retrievedComment).isNotNull();
                Assertions.assertThat(retrievedComment.getId()).isNotNull();
                Assertions.assertThat(retrievedComment.getContent()).isEqualTo("Comment 1");
        }

        @Test
        public void shouldGetAllCommentsByPost() {
                Channel channel = Channel.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                                .content("Channel 1").build();
                Channel savedChannel = channelRepository.save(channel);
                Post post = Post.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                                .content("Post 1").channel(savedChannel).build();
                Post savedPost = postRepository.save(post);
                Comment comment = Comment.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                                .content("Comment 1").post(savedPost).build();
                Comment comment2 = Comment.builder().id(2L).createdDate(Instant.now()).updatedDate(Instant.now())
                                .content("Comment 2").post(savedPost).build();

                commentRepository.save(comment);
                commentRepository.save(comment2);
                List<Comment> retrievedComments = commentRepository.findByPostId(savedPost.getId());

                Assertions.assertThat(retrievedComments).isNotNull();
                Assertions.assertThat(retrievedComments.size()).isEqualTo(2);
        }

        @Test
        public void shouldUpdateComment() {
                Channel channel = Channel.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                                .content("Channel 1").build();
                Channel savedChannel = channelRepository.save(channel);
                Post post = Post.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                                .content("Post 1").channel(savedChannel).build();
                Post savedPost = postRepository.save(post);
                Comment comment = Comment.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                                .content("Comment 1").post(savedPost).build();

                Comment savedComment = commentRepository.save(comment);
                savedComment.setContent("Updated Comment");
                Comment updatedComment = commentRepository.save(savedComment);

                Assertions.assertThat(updatedComment).isNotNull();
                Assertions.assertThat(updatedComment.getId()).isNotNull();
                Assertions.assertThat(updatedComment.getContent()).isEqualTo("Updated Comment");
        }

        @Test
        public void shouldDeleteComment() {
                Channel channel = Channel.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                                .content("Channel 1").build();
                Channel savedChannel = channelRepository.save(channel);
                Post post = Post.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                                .content("Post 1").channel(savedChannel).build();
                Post savedPost = postRepository.save(post);
                Comment comment = Comment.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                                .content("Comment 1").post(savedPost).build();

                commentRepository.save(comment);
                commentRepository.deleteById(comment.getId());
                Optional<Comment> deletedComment = commentRepository.findById(comment.getId());

                Assertions.assertThat(deletedComment).isEmpty();
        }

        @Test
        public void shouldDeleteCommentsByPost() {
                Channel channel = Channel.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                                .content("Channel 1").build();
                Channel savedChannel = channelRepository.save(channel);
                Post post = Post.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                                .content("Post 1").channel(savedChannel).build();
                Post savedPost = postRepository.save(post);
                Comment comment = Comment.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                                .content("Comment 1").post(savedPost).build();
                Comment comment2 = Comment.builder().id(2L).createdDate(Instant.now()).updatedDate(Instant.now())
                                .content("Comment 2").post(savedPost).build();

                commentRepository.save(comment);
                commentRepository.save(comment2);
                commentRepository.deleteByPostId(savedPost.getId());
                List<Comment> retrievedComments = commentRepository.findByPostId(savedPost.getId());

                Assertions.assertThat(retrievedComments).isNotNull();
                Assertions.assertThat(retrievedComments.size()).isEqualTo(0);
        }

}
