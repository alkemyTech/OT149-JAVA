package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.NewDto;
import com.alkemy.ong.model.New;
import org.mapstruct.Mapper;

@Mapper
public interface NewMapper {
    New toNew(NewDto dto);
    NewDto toDto(New news);
}
