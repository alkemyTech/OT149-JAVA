package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.mapper.TestimonialMapper;
import com.alkemy.ong.model.Testimonial;
import com.alkemy.ong.repository.TestimonialsRepository;
import com.alkemy.ong.service.TestimonialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestimonialServiceImpl implements TestimonialService {

	private final TestimonialMapper testimonialMapper;
	private final TestimonialsRepository testimonialsRepository;

	@Override
	public void saveTestimonial(TestimonialDto dto){

		Testimonial testimonial = testimonialMapper.toTestimonial(dto);
		testimonialsRepository.save(testimonial);
	}
}
