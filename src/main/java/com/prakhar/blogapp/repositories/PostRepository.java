package com.prakhar.blogapp.repositories;

import com.prakhar.blogapp.models.Category;
import com.prakhar.blogapp.models.Post;
import com.prakhar.blogapp.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
    Page<Post> findByTitleContaining(String title, Pageable pageable);
}
