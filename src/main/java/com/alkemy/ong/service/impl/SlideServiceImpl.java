package com.alkemy.ong.service.impl;

import com.alkemy.ong.mapper.SlideMapper;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.service.SlideService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.alkemy.ong.dto.SlideDetailDto;
import com.alkemy.ong.model.Slide;

import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SlideServiceImpl implements SlideService {

    private final SlideRepository repository;
    private final SlideMapper mapper;

    @Override
	public List<SlideDetailDto> getAllSlides() {
		List<Slide> slides = repository.findAll();
		List<SlideDetailDto> slidesDetailDto = mapper.toSlideDetailDto(slides);
		return slidesDetailDto;
	}

}
	