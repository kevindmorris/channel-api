package com.channel.channelapi.service;

import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.channel.channelapi.exception.BaseException;
import com.channel.channelapi.model.Channel;
import com.channel.channelapi.model.Post;
import com.channel.channelapi.repository.ChannelRepository;
import com.channel.channelapi.repository.PostRepository;

@ExtendWith(MockitoExtension.class)
public class PostServiceTests {

        @Mock
        private ChannelRepository channelRepository;
        @Mock
        private PostRepository postRepository;

        @InjectMocks
        private PostServiceImpl postService;

        @Test
        public void shouldCreatePost() throws BaseException {
                Channel channel = Channel.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                                .content("Channel 1").build();
                Post post = Post.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                                .content("Post 1")
                                .channel(channel).build();

                when(channelRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(channel));
                when(postRepository.save(Mockito.any(Post.class))).thenReturn(post);

                Post savedPost = postService.createPost(1L, post);

                Assertions.assertThat(savedPost).isNotNull();
                Assertions.assertThat(savedPost.getContent()).isEqualTo("Post 1");
                Assertions.assertThat(savedPost.getChannel().getContent()).isEqualTo("Channel 1");
        }

        @Test
        public void shouldGetPost() throws BaseException {
                Channel channel = Channel.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                                .content("Channel 1").build();
                Post post = Post.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                                .content("Post 1").channel(channel).build();

                when(postRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(post));

                Post retrivedPost = postService.getPost(1L);

                Assertions.assertThat(retrivedPost).isNotNull();
                Assertions.assertThat(retrivedPost.getContent()).isEqualTo("Post 1");
        }

        @Test
        public void shouldGetPostsByChannel() throws BaseException {
                Channel channel = Channel.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                                .content("Channel 1").build();
                Post post = Post.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                                .content("Post 1")
                                .channel(channel).build();
                Post post2 = Post.builder().id(2L).createdDate(Instant.now()).updatedDate(Instant.now())
                                .content("Post 1")
                                .channel(channel).build();
                List<Post> posts = List.of(post, post2);

                when(postRepository.findByChannelId(Mockito.anyLong())).thenReturn(posts);

                List<Post> retrivedPosts = postService.getPostsByChannel(1L);

                Assertions.assertThat(retrivedPosts).isNotNull();
                Assertions.assertThat(retrivedPosts.size()).isEqualTo(2);
        }

        @Test
        public void shouldUpdatePost() throws BaseException {
                Channel channel = Channel.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                                .content("Channel 1").build();
                Post post = Post.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                                .content("Updated Post").channel(channel).build();

                when(postRepository.save(Mockito.any(Post.class))).thenReturn(post);
                when(postRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(post));

                Post updatedPost = postService.updatePost(1L, post);

                Assertions.assertThat(updatedPost).isNotNull();
                Assertions.assertThat(updatedPost.getContent()).isEqualTo("Updated Post");
        }

        @Test
        public void shouldDeletePost() throws BaseException {
                Channel channel = Channel.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                                .content("Channel 1").build();
                Post post = Post.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                                .content("Post 1")
                                .channel(channel).build();

                when(postRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(post));

                postService.deletePost(1L);

                Mockito.verify(postRepository).deleteById(1L);
        }

        @Test
        public void shouldDeletePostsByChannel() throws BaseException {
                postService.deletePostsByChannel(1L);

                Mockito.verify(postRepository).deleteByChannelId(1L);
        }

}
