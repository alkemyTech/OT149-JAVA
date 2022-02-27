package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.NewsResponseDto;
import com.alkemy.ong.model.New;
import org.mapstruct.Mapper;

@Mapper
public interface NewsResponseMapper {

    New toNews(NewsResponseDto dto);
    NewsResponseDto toNewsResponseDto(New model);
}
