package com.alkemy.ong.service;

import com.alkemy.ong.dto.SlideDto;
import java.util.List;
import com.alkemy.ong.dto.SlideDetailDto;

public interface SlideService {

	SlideDetailDto getSlideById(Long id);

	Long saveSlide(SlideDto dto);

	List<SlideDetailDto> getAllSlides();

	void deleteSlide(Long id);

}
