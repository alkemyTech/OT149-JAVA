package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.SlideDetailDto;
import com.alkemy.ong.exception.NewNotFoundException;
import com.alkemy.ong.mapper.SlideMapper;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.service.SlideService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SlideServiceImpl implements SlideService {

    private final SlideRepository repository;
    private final SlideMapper mapper;
    
	@Override
	public SlideDetailDto getSlideById(Long id) {
        return repository.findById(id)
        		.map(mapper::toSlideDetailDto)
        		.orElseThrow(() -> new NewNotFoundException());
	}
}
	