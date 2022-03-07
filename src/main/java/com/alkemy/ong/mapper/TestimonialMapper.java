package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.PageDto;
import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.model.Testimonial;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface TestimonialMapper {

	TestimonialDto toDto(Testimonial testimonial);
	Testimonial toTestimonial(TestimonialDto dto);

	private List<TestimonialDto> toTestimonialDtoList(Page<Testimonial> testimonialPage) {

		List<TestimonialDto> testimonialDtoList = new ArrayList<>();
		if (testimonialPage.hasContent()) {
			testimonialDtoList = testimonialPage
					.getContent()
					.stream()
					.map(testimonial -> {
				return new TestimonialDto(
						testimonial.getName(),
						testimonial.getImage(),
						testimonial.getContent());
			}).collect(Collectors.toList());
		}
		return testimonialDtoList;
	}

	default PageDto<TestimonialDto> toPageDto(
			Page<Testimonial> testimonialPage,
			Integer pageNumber,
			Integer totalPages) {
		PageDto<TestimonialDto> pageDto = new PageDto<>();

		pageDto.setTotalPages(totalPages);
		if (testimonialPage.hasNext()) {
			pageDto.setNextPage("/testimonials?page=" + (pageNumber + 1));
		}
		if (testimonialPage.hasPrevious()) {
			pageDto.setPreviousPage("/testimonials?page=" + (pageNumber - 1));
		}
		pageDto.setList(toTestimonialDtoList(testimonialPage));
		return pageDto;
	}

}
