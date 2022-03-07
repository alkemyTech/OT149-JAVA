package com.alkemy.ong.service;

import com.alkemy.ong.dto.CategoryDetailDto;
import com.alkemy.ong.dto.CategoryPutDto;

public interface CategoryService {
    CategoryDetailDto getCategoryById(Long id);
    void deleteCategory(Long id);
    void updateCategory(Long id, CategoryPutDto putDto);
}
