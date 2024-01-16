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
import com.channel.channelapi.model.Post;
import com.channel.channelapi.service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = PostController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class PostControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldCreatePost() throws Exception {
        Channel channel = Channel.builder().content("Channel 1").build();
        Post post = Post.builder().content("Post 1").channel(channel).build();
        when(postService.createPost(Mockito.anyLong(), Mockito.any(Post.class))).thenReturn(post);

        mockMvc.perform(post("/channels/{id}/posts", 1L).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(post))).andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Post 1"));
    }

    @Test
    public void shouldGetPost() throws Exception {
        Channel channel = Channel.builder().content("Channel 1").build();
        Post post = Post.builder().content("Post 1").channel(channel).build();
        when(postService.getPost(Mockito.anyLong())).thenReturn(post);

        mockMvc.perform(get("/posts/{id}", 1L)).andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Post 1"));
    }

    @Test
    public void shouldGetPostsByChannel() throws Exception {
        Channel channel = Channel.builder().content("Channel 1").build();
        Post post = Post.builder().content("Post 1").channel(channel).build();
        Post post2 = Post.builder().content("Post 2").channel(channel).build();
        List<Post> posts = List.of(post, post2);
        when(postService.getPostsByChannel(Mockito.anyLong())).thenReturn(posts);

        mockMvc.perform(get("/channels/{id}/posts", 1L)).andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void shouldUpdatePost() throws Exception {
        Channel channel = Channel.builder().content("Channel 1").build();
        Post post = Post.builder().content("Updated Post").channel(channel).build();
        when(postService.updatePost(Mockito.anyLong(), Mockito.any(Post.class))).thenReturn(post);

        mockMvc.perform(put("/posts/{id}", 1L).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(post))).andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Updated Post"));
    }

    @Test
    public void shouldDeletePost() throws Exception {
        doNothing().when(postService).deletePost(Mockito.anyLong());

        mockMvc.perform(delete("/posts/{id}", 1L)).andExpect(status().isOk());
    }

    @Test
    public void shouldDeletePostsByChannel() throws Exception {
        doNothing().when(postService).deletePost(Mockito.anyLong());

        mockMvc.perform(delete("/channels/{id}/posts", 1L)).andExpect(status().isOk());
    }

}
