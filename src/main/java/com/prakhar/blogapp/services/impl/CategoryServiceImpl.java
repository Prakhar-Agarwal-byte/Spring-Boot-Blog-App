package com.prakhar.blogapp.services.impl;

import com.prakhar.blogapp.payloads.CategoryDto;
import com.prakhar.blogapp.exceptions.ResourceNotFoundException;
import com.prakhar.blogapp.models.Category;
import com.prakhar.blogapp.repositories.CategoryRepository;
import com.prakhar.blogapp.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ModelMapper mapper;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDTO) {
        Category category = mapper.map(categoryDTO, Category.class);
        Category savedCategory = categoryRepository.save(category);
        return mapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public Page<CategoryDto> getAllCategories(Pageable pageable) {
        Page<Category> page = categoryRepository.findAll(pageable);
        List<Category> allCategories = page.getContent();
        List<CategoryDto> categoryDtos = allCategories.stream().map(user -> mapper.map(user, CategoryDto.class)).toList();
        return new PageImpl<>(categoryDtos, pageable, categoryDtos.size());
    }

    @Override
    public CategoryDto getCategoryById(Integer id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id));
        return mapper.map(category, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDTO, Integer id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id));
        category.setTitle(categoryDTO.getTitle());
        category.setDescription(categoryDTO.getDescription());
        Category savedCategory = categoryRepository.save(category);
        return mapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }
}
