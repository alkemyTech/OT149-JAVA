package com.alkemy.ong.service;

import com.alkemy.ong.dto.CategoryDto;

public interface CategoryService  {
    void deleteCategory(Long id);
    void createCategory(CategoryDto dto);
}
