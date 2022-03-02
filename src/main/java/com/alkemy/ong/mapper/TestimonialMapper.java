package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.model.Testimonial;
import org.aspectj.weaver.ast.Test;
import org.mapstruct.Mapper;

@Mapper
public interface TestimonialMapper {

	TestimonialDto toDto(Test testimonial);
	Testimonial toTestimonial(TestimonialDto dto);

}
