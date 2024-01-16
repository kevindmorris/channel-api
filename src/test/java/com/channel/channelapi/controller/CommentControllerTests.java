package com.channel.channelapi.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.channel.channelapi.model.Channel;
import com.channel.channelapi.model.Comment;
import com.channel.channelapi.model.Post;
import com.channel.channelapi.service.CommentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = CommentController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class CommentControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldCreateComment() throws Exception {
        Channel channel = Channel.builder().content("Channel 1").build();
        Post post = Post.builder().content("Post 1").channel(channel).build();
        Comment comment = Comment.builder().content("Comment 1").post(post).build();
        when(commentService.createComment(Mockito.anyLong(), Mockito.any(Comment.class))).thenReturn(comment);

        mockMvc.perform(post("/posts/{id}/comments", 1L).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comment))).andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Comment 1"));
    }

    @Test
    public void shouldGetComment() throws Exception {
        Channel channel = Channel.builder().content("Channel 1").build();
        Post post = Post.builder().content("Post 1").channel(channel).build();
        Comment comment = Comment.builder().content("Comment 1").post(post).build();
        when(commentService.getComment(Mockito.anyLong())).thenReturn(comment);

        mockMvc.perform(get("/comments/{id}", 1L)).andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Comment 1"));
    }

    @Test
    public void shouldGetCommentsByPost() throws Exception {
        Channel channel = Channel.builder().content("Channel 1").build();
        Post post = Post.builder().content("Post 1").channel(channel).build();
        Comment comment = Comment.builder().content("Comment 1").post(post).build();
        Comment comment2 = Comment.builder().content("Comment 2").post(post).build();
        List<Comment> comments = List.of(comment, comment2);
        when(commentService.getCommentsByPost(Mockito.anyLong())).thenReturn(comments);

        mockMvc.perform(get("/posts/{id}/comments", 1L)).andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void shouldUpdateComment() throws Exception {
        Channel channel = Channel.builder().content("Channel 1").build();
        Post post = Post.builder().content("Post 1").channel(channel).build();
        Comment comment = Comment.builder().content("Updated Comment").post(post).build();
        when(commentService.updateComment(Mockito.anyLong(), Mockito.any(Comment.class))).thenReturn(comment);

        mockMvc.perform(put("/comments/{id}", 1L).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comment))).andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Updated Comment"));
    }

    @Test
    public void shouldDeleteComment() throws Exception {
        doNothing().when(commentService).deleteComment(Mockito.anyLong());

        mockMvc.perform(delete("/comments/{id}", 1L)).andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteCommentsByPost() throws Exception {
        doNothing().when(commentService).deleteComment(Mockito.anyLong());

        mockMvc.perform(delete("/posts/{id}/comments", 1L)).andExpect(status().isOk());
    }

}
