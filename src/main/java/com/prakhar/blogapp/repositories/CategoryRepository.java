package com.prakhar.blogapp.repositories;

import com.prakhar.blogapp.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
