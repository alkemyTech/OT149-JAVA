package com.alkemy.ong.service;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.dto.CategoryDetailDto;

public interface CategoryService  {

    void createCategory(CategoryDto dto);
    CategoryDetailDto getCategoryById(Long id);
    void deleteCategory(Long id);
    void updateCategory(Long id, CategoryPutDto putDto);
}
