package com.alkemy.ong.service;

public interface CategoryService  {
    void deleteCategory(Long id);
    void updateCategory(Long id, CategoryPutDto putDto);
}
