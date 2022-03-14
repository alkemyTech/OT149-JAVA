package com.alkemy.ong.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.mapper.SlideMapper;
import com.alkemy.ong.model.Organization;
import com.alkemy.ong.model.Slide;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.service.OrganizationService;
import com.alkemy.ong.service.SlideService;

import lombok.RequiredArgsConstructor;

import com.alkemy.ong.dto.SlideDetailDto;
import com.alkemy.ong.model.Slide;

import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SlideServiceImpl implements SlideService {

	private final SlideRepository repository;
	private final SlideMapper mapper;
	private final AmazonS3ServiceImpl amazonService;
	private final OrganizationService orgService;

	@Transactional
	@Override
	public Long saveSlide(SlideDto dto) {

		String fileUrl;
		fileUrl = amazonService.uploadImage64(dto.getImageB64());

		Organization org = orgService.findById(dto.getOrganizationId());

		Slide slide = mapper.toSlide(dto);
		slide.setImageUrl(fileUrl);
		slide.setOrganization(org);

		if (slide.getOrder() == null) {
			Integer maxOrder = repository.findMaxOrder(slide.getOrganization().getId());
			if (maxOrder == null) {
				slide.setOrder(1);
			} else {
				slide.setOrder(maxOrder + 1);
			}
		}

		Slide saved = repository.save(slide);

		return saved.getId();
	}

  @Override
  public List<SlideDetailDto> getAllSlides() {
    List<Slide> slides = repository.findAll();
    List<SlideDetailDto> slidesDetailDto = mapper.toSlideDetailDto(slides);
    return slidesDetailDto;
  }

}
