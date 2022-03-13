package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.CategoryListDto;
import com.alkemy.ong.model.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CategoryListMapper {
    List<CategoryListDto> toCategoryListDto(List<Category> models);
}
