package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.model.Testimonial;
import org.mapstruct.Mapper;

@Mapper
public interface TestimonialMapper {

	TestimonialDto toDto(Testimonial testimonial);
	Testimonial toTestimonial(TestimonialDto dto);
}
