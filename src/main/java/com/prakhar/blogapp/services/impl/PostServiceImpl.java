package com.prakhar.blogapp.services.impl;

import com.prakhar.blogapp.payloads.PostDto;
import com.prakhar.blogapp.exceptions.ResourceNotFoundException;
import com.prakhar.blogapp.models.Category;
import com.prakhar.blogapp.models.Post;
import com.prakhar.blogapp.models.User;
import com.prakhar.blogapp.repositories.CategoryRepository;
import com.prakhar.blogapp.repositories.PostRepository;
import com.prakhar.blogapp.repositories.UserRepository;
import com.prakhar.blogapp.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    ModelMapper mapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    PostRepository postRepository;
    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user =  userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("No User with id " + userId));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("No Category with id " + categoryId));
        Post post = mapper.map(postDto, Post.class);
        post.setCreatedAt(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post savedPost = postRepository.save(post);
        return mapper.map(savedPost, PostDto.class);
    }

    @Override
    public Page<PostDto> getAllPosts(String title, Pageable pageable) {
        Page<Post> page;
        if (Objects.nonNull(title) && !title.isBlank()) {
            page = postRepository.findByTitleContaining(title, pageable);
        } else {
            page = postRepository.findAll(pageable);
        }
        List<Post> allPosts = page.getContent();
        List<PostDto> postDtos = allPosts.stream().map(user -> mapper.map(user, PostDto.class)).toList();
        return new PageImpl<>(postDtos, pageable, postDtos.size());
    }

    @Override
    public PostDto getPostById(Integer id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Post with id " + id));
        return mapper.map(post, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post not found with id " + id));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageUrl(postDto.getImageUrl());
        Post savedPost = postRepository.save(post);
        return mapper.map(savedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer id) {
        postRepository.deleteById(id);
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        User user =  userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("No User with id " + userId));
        List<Post> posts = postRepository.findByUser(user);
        return posts.stream().map(p -> mapper.map(p, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("No Category with id " + categoryId));
        List<Post> posts = postRepository.findByCategory(category);
        return posts.stream().map(p -> mapper.map(p, PostDto.class)).collect(Collectors.toList());
    }
}
