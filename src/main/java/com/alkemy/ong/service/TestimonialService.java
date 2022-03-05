package com.alkemy.ong.service;

import com.alkemy.ong.dto.TestimonialDto;
import org.springframework.security.access.annotation.Secured;

public interface TestimonialService {
	@Secured("ROLE_ADMIN")
	void saveTestimonial(TestimonialDto dto);
}
