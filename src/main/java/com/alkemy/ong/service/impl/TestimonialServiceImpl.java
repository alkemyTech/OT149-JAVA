package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.exception.TestimonialNotFoundException;
import com.alkemy.ong.mapper.TestimonialMapper;
import com.alkemy.ong.model.Testimonial;
import com.alkemy.ong.repository.TestimonialsRepository;
import com.alkemy.ong.service.TestimonialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestimonialServiceImpl implements TestimonialService {

	private final TestimonialsRepository testimonialsRepository;
	private final TestimonialMapper testimonialMapper;

	@Override
	public TestimonialDto testimonialPut(Long id, TestimonialDto dto){
		if(!testimonialsRepository.existsById(id)){
			throw new TestimonialNotFoundException();
		}
		Testimonial testimonial = testimonialMapper.toTestimonial(dto);
		return testimonialMapper.toDto(testimonialsRepository.save(testimonial));
	}
}
