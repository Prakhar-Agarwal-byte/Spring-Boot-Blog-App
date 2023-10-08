package com.prakhar.blogapp.services.impl;

import com.prakhar.blogapp.payloads.CommentDto;
import com.prakhar.blogapp.exceptions.ResourceNotFoundException;
import com.prakhar.blogapp.models.Comment;
import com.prakhar.blogapp.models.Post;
import com.prakhar.blogapp.models.User;
import com.prakhar.blogapp.repositories.CommentRepository;
import com.prakhar.blogapp.repositories.PostRepository;
import com.prakhar.blogapp.repositories.UserRepository;
import com.prakhar.blogapp.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    ModelMapper mapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("No User with id " + userId));
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("No Post with id " + postId));
        Comment comment = mapper.map(commentDto, Comment.class);
        comment.setUser(user);
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);
        return mapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        commentRepository.deleteById(commentId);
    }
}
