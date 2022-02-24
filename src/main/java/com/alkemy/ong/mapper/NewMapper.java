package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.NewDetailDto;
import org.mapstruct.Mapper;

@Mapper
public interface NewMapper {
    New toNew(NewDetailDto dto);
    NewDetailDto toNewDetailDto(New newModel);
}
