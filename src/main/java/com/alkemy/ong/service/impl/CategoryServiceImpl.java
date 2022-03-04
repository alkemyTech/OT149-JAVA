package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CategoryDetailDto;
import com.alkemy.ong.exception.CategoryNotFoundException;
import com.alkemy.ong.mapper.CategoryMapper;
import com.alkemy.ong.repository.CategoriesRepository;
import com.alkemy.ong.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoriesRepository repository;
    @Autowired
    CategoryMapper mapper;

    public CategoryDetailDto getCategoryById(Long id){
        return repository.findById(id).map(category -> {
            return mapper.toCategoryDetailDto(category);
        }).orElseThrow(()->{
            throw new CategoryNotFoundException();
        });

    }
}
