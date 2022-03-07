package com.alkemy.ong.service;

import com.alkemy.ong.dto.PageDto;
import com.alkemy.ong.dto.TestimonialDto;
import org.springframework.security.access.annotation.Secured;

public interface TestimonialService {
	@Secured("ROLE_ADMIN")
	TestimonialDto testimonialPut(Long id, TestimonialDto dto);

	@Secured("ROLE_ADMIN")
	void saveTestimonial(TestimonialDto dto);
	PageDto<TestimonialDto> getPage(Integer page, Integer sizePage, String sortBy);
}
