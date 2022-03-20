package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.dto.CategoryDetailDto;
import com.alkemy.ong.dto.CategoryListDto;
import com.alkemy.ong.dto.CategoryPutDto;
import com.alkemy.ong.exception.CategoryNotFoundException;
import com.alkemy.ong.mapper.CategoryListMapper;
import com.alkemy.ong.mapper.CategoryMapper;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.repository.CategoriesRepository;
import com.alkemy.ong.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private final CategoriesRepository repository;
    @Autowired
    private final CategoryMapper mapper;

    private final CategoryListMapper listMapper;

    @Transactional(readOnly = true)
    @Override
    public CategoryDetailDto getCategoryById(Long id) {
        return repository.findById(id).map(category -> {
            return mapper.toCategoryDetailDto(category);
        }).orElseThrow(() -> {
            throw new CategoryNotFoundException("Category not found with id "+id);
        });
    }

    @Transactional
    @Override
    public void deleteCategory(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new CategoryNotFoundException("Category not found with id "+id);
        }
        repository.deleteById(id);
    }

    @Transactional
    @Override
    public long createCategory(CategoryDto dto) {
        Category category = mapper.toCategory(dto);
        repository.save(category);
        long categoryId = category.getId();

        return categoryId;
    }

    @Transactional
    @Override
    public void updateCategory(Long id, CategoryPutDto putDto) {
        repository.findById(id).map(category -> {
            category.setName(putDto.getName());
            category.setDescription(putDto.getDescription());
            category.setImage(putDto.getImage());
            return repository.save(category);
        }).orElseThrow(() -> {
            throw new CategoryNotFoundException("Category not found with id "+id);
        });
    }

    @Transactional(readOnly = true)
    @Override
    public List<CategoryListDto> getAllCategories() {
        return listMapper.toCategoryListDto(repository.findAll());
    }
}

