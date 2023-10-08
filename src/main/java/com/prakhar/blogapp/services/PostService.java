package com.prakhar.blogapp.services;

import com.prakhar.blogapp.payloads.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto post, Integer userId, Integer categoryId);
    Page<PostDto> getAllPosts(String title, Pageable pageable);
    PostDto getPostById(Integer id);
    PostDto updatePost(PostDto user, Integer id);
    void deletePost(Integer id);
    List<PostDto> getPostsByUser(Integer userId);
    List<PostDto> getPostsByCategory(Integer categoryId);
}
