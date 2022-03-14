package com.alkemy.ong.service;

import java.util.List;
import com.alkemy.ong.dto.SlideDetailDto;

public interface SlideService {

	SlideDetailDto getSlideById(Long id);
  List<SlideDetailDto> getAllSlides();

}
