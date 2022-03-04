package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CategoryPutDto;
import com.alkemy.ong.exception.CategoryNotFoundException;
import com.alkemy.ong.repository.CategoriesRepository;
import com.alkemy.ong.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoriesRepository repository;

    public void updateCategory(Long id, CategoryPutDto putDto){
        repository.findById(id).map(category -> {
            category.setName(putDto.getName());
            category.setDescription(putDto.getDescription());
            category.setImage(putDto.getImage());
            return repository.save(category);
        }).orElseThrow(()->{
            throw new CategoryNotFoundException();
        });
    }
}
