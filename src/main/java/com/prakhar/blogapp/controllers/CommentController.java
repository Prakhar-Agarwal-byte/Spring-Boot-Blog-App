package com.prakhar.blogapp.controllers;

import com.prakhar.blogapp.payloads.CommentDto;
import com.prakhar.blogapp.services.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/posts/{postId}/users/{userId}/comments")
    public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CommentDto commentDto, @PathVariable Integer postId, @PathVariable Integer userId) {
        CommentDto createdCommentDto = commentService.createComment(commentDto, postId, userId);
        return new ResponseEntity<>(createdCommentDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Integer commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(Map.of("message", "Comment deleted successfully"), HttpStatus.OK);
    }
}
