package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.dto.TestimonialPagedList;
import com.alkemy.ong.exception.TestimonialNotFoundException;
import com.alkemy.ong.mapper.TestimonialMapper;
import com.alkemy.ong.model.Testimonial;
import com.alkemy.ong.repository.TestimonialsRepository;
import com.alkemy.ong.service.TestimonialService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    @Override
    public TestimonialDto testimonialPut(Long id, TestimonialDto dto) {
        return testimonialsRepository.findById(id).map(testimonial -> {
           testimonial.setName(dto.getName());
           testimonial.setContent(dto.getContent());
           testimonial.setImage(dto.getImage());
            testimonialsRepository.save(testimonial);
           return testimonialMapper.toDto(testimonial);
        }).orElseThrow(() -> new TestimonialNotFoundException("Testimonial not found."));
    }

    @Override
    public TestimonialPagedList pagedList(PageRequest pageRequest) {

        Page<Testimonial> page = testimonialsRepository.findAll(pageRequest);

        final List<TestimonialDto> list = page.getContent().stream().map(testimonialMapper::toDto).collect(Collectors.toList());
        final PageRequest of = PageRequest.of(page.getPageable().getPageNumber(), page.getPageable().getPageSize());
        final long totalElements = page.getTotalElements();

        return new TestimonialPagedList(list, of, totalElements);
    }

    @Override
    public void deleteTestimonial(Long id) {
        if (!this.testimonialsRepository.existsById(id)) {
            throw new TestimonialNotFoundException("Testimonial not found with id " + id);
        }
        this.testimonialsRepository.deleteById(id);
    }
}