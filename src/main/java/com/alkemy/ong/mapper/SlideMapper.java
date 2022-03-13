package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.SlideDetailDto;
import com.alkemy.ong.model.Slide;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper
public interface SlideMapper {

    List<SlideDetailDto> toSlideDetailDto(List<Slide> slides);

}
