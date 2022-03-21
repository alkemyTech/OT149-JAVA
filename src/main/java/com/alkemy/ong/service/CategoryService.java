package com.alkemy.ong.service;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.dto.CategoryDetailDto;

import com.alkemy.ong.dto.CategoryPagedList;

import com.alkemy.ong.dto.CategoryListDto;

import com.alkemy.ong.dto.CategoryPutDto;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CategoryService  {

    long createCategory(CategoryDto dto);
    CategoryDetailDto getCategoryById(Long id);
    void deleteCategory(Long id);
    void updateCategory(Long id, CategoryPutDto putDto);
    CategoryPagedList getAllCategories(PageRequest pageRequest);
}
