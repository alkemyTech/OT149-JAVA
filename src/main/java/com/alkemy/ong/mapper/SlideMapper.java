package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.model.Slide;

import org.mapstruct.Mapper;

@Mapper
public interface SlideMapper {
    SlideDto toSlideDto(Slide slide);
    Slide toSlide(SlideDto slideDto);

}
