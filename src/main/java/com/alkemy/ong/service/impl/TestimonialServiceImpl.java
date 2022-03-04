package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.PageDto;
import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.exception.TestimonialNotFoundException;
import com.alkemy.ong.mapper.TestimonialMapper;
import com.alkemy.ong.model.Testimonial;
import com.alkemy.ong.repository.TestimonialsRepository;
import com.alkemy.ong.service.TestimonialService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestimonialServiceImpl implements TestimonialService {

	private final TestimonialsRepository testimonialsRepository;
	private final TestimonialMapper testimonialMapper;

	@Override
	public void saveTestimonial(TestimonialDto dto) {
		Testimonial testimonial = testimonialMapper.toTestimonial(dto);
		testimonialsRepository.save(testimonial);
	}

	@Override
	public PageDto<TestimonialDto> getPage(Integer page, Integer sizePage, String sortBy) {

		Pageable pageable = PageRequest.of(page, sizePage, Sort.by(sortBy));
		Page<Testimonial> pageRecovered = testimonialsRepository.findAll(pageable);
		Integer totalPages=pageRecovered.getTotalPages();

		if(totalPages<page) {
			throw new TestimonialNotFoundException();
		}
		return testimonialMapper.toPageDto(pageRecovered, page,totalPages);
	}
}
