package com.alkemy.ong.service;

import com.alkemy.ong.dto.TestimonialDto;
import org.springframework.security.access.annotation.Secured;

public interface TestimonialService {
	
	@Secured("ROLE_ADMIN")
	TestimonialDto testimonialPut(Long id, TestimonialDto dto);

	@Secured("ROLE_ADMIN")
	void saveTestimonial(TestimonialDto dto);

	void deleteTestimonial(Long id);
}
