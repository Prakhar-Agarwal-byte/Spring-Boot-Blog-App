package com.prakhar.blogapp.repositories;

import com.prakhar.blogapp.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
