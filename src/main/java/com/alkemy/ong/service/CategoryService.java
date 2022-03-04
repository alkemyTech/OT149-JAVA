package com.alkemy.ong.service;

import com.alkemy.ong.dto.CategoryPutDto;

public interface CategoryService {
    void updateCategory(Long id, CategoryPutDto putDto);
}
