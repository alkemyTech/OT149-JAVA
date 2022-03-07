package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.CategoryDetailDto;
import com.alkemy.ong.model.Category;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryMapper {
    CategoryDetailDto toCategoryDetailDto(Category category);
}
