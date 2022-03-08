package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.NewDetailDto;
import com.alkemy.ong.dto.NewDto;
import com.alkemy.ong.model.New;
import org.mapstruct.Mapper;

@Mapper
public interface NewMapper {
    NewDetailDto toNewDetailDto(New newModel);
    New toNew(NewDetailDto dto);
    New toNew(NewDto dto);

}
