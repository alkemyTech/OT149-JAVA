package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.NewResponseDto;
import com.alkemy.ong.model.New;
import org.mapstruct.Mapper;

@Mapper
public interface NewResponseMapper {

    New toNews(NewResponseDto dto);
    NewResponseDto toNewsResponseDto(New model);
}
