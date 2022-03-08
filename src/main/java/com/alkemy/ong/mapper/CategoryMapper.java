package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.dto.CategoryDetailDto;
import com.alkemy.ong.model.Category;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryMapper {
    Category toCategory (CategoryDto dto);
    CategoryDetailDto toCategoryDetailDto(Category category);
}
