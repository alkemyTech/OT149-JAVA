package com.alkemy.ong.service.impl;

import com.alkemy.ong.exception.CategoryNotFoundException;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.repository.CategoriesRepository;
import com.alkemy.ong.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private final CategoriesRepository repository;


    @Override
    @Transactional
    public void deleteCategory(Long id){
        if (repository.findById(id).isEmpty()){
            throw new CategoryNotFoundException();
        }
        repository.deleteById(id);
    }
}