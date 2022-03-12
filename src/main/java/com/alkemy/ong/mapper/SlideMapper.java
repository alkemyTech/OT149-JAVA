package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.model.Slide;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface SlideMapper {
	
	@Mapping(source = "slide.imageUrl", target = "imageB64")
	@Mapping(source = "slide.organization.id", target = "organizationId")
    SlideDto toSlideDto(Slide slide);
	
	@Mapping(source = "slideDto.imageB64", target = "imageUrl")

    Slide toSlide(SlideDto slideDto);

}
