package com.prakhar.blogapp.services;

import com.prakhar.blogapp.payloads.CategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto category);
    Page<CategoryDto> getAllCategories(Pageable pageable);
    CategoryDto getCategoryById(Integer id);
    CategoryDto updateCategory(CategoryDto category, Integer id);
    void deleteCategory(Integer id);
}
