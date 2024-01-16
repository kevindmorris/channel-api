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
import com.channel.channelapi.model.Comment;
import com.channel.channelapi.model.Post;
import com.channel.channelapi.repository.CommentRepository;
import com.channel.channelapi.repository.PostRepository;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTests {

    @Mock
    private PostRepository postRepository;
    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    @Test
    public void shouldCreateComment() throws BaseException {
        Channel channel = Channel.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                .content("Channel 1").build();
        Post post = Post.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                .content("Post 1")
                .channel(channel).build();
        Comment comment = Comment.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                .content("Comment 1").post(post).build();

        when(postRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(post));
        when(commentRepository.save(Mockito.any(Comment.class))).thenReturn(comment);

        Comment savedComment = commentService.createComment(1L, comment);

        Assertions.assertThat(savedComment).isNotNull();
        Assertions.assertThat(savedComment.getContent()).isEqualTo("Comment 1");
        Assertions.assertThat(savedComment.getPost().getContent()).isEqualTo("Post 1");
    }

    @Test
    public void shouldGetComment() throws BaseException {
        Channel channel = Channel.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                .content("Channel 1").build();
        Post post = Post.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                .content("Post 1")
                .channel(channel).build();
        Comment comment = Comment.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                .content("Comment 1").post(post).build();

        when(commentRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(comment));

        Comment retrivedComment = commentService.getComment(1L);

        Assertions.assertThat(retrivedComment).isNotNull();
        Assertions.assertThat(retrivedComment.getContent()).isEqualTo("Comment 1");
    }

    @Test
    public void shouldGetCommentsByPost() throws BaseException {
        Channel channel = Channel.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                .content("Channel 1").build();
        Post post = Post.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                .content("Post 1")
                .channel(channel).build();
        Comment comment = Comment.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                .content("Comment 1").post(post).build();
        Comment comment2 = Comment.builder().id(2L).createdDate(Instant.now()).updatedDate(Instant.now())
                .content("Comment 1").post(post).build();
        List<Comment> comments = List.of(comment, comment2);

        when(commentRepository.findByPostId(Mockito.anyLong())).thenReturn(comments);

        List<Comment> retrivedComments = commentService.getCommentsByPost(1L);

        Assertions.assertThat(retrivedComments).isNotNull();
        Assertions.assertThat(retrivedComments.size()).isEqualTo(2);
    }

    @Test
    public void shouldUpdateComment() throws BaseException {
        Channel channel = Channel.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                .content("Channel 1").build();
        Post post = Post.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                .content("Post 1")
                .channel(channel).build();
        Comment comment = Comment.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                .content("Updated Comment").post(post).build();

        when(commentRepository.save(Mockito.any(Comment.class))).thenReturn(comment);
        when(commentRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(comment));

        Comment updatedComment = commentService.updateComment(1L, comment);

        Assertions.assertThat(updatedComment).isNotNull();
        Assertions.assertThat(updatedComment.getContent()).isEqualTo("Updated Comment");
    }

    @Test
    public void shouldDeleteComment() throws BaseException {
        Channel channel = Channel.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                .content("Channel 1").build();
        Post post = Post.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                .content("Post 1")
                .channel(channel).build();
        Comment comment = Comment.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                .content("Comment 1").post(post).build();

        when(commentRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(comment));

        commentService.deleteComment(1L);

        Mockito.verify(commentRepository).deleteById(1L);
    }

    @Test
    public void shouldDeleteCommentsByPost() throws BaseException {
        commentService.deleteCommentsByPost(1L);

        Mockito.verify(commentRepository).deleteByPostId(1L);
    }

}
