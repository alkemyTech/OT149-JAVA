package com.alkemy.ong.service.impl;

import java.util.Collections;
import java.util.Comparator;
import com.alkemy.ong.exception.NewNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alkemy.ong.dto.SlideDetailDto;
import com.alkemy.ong.dto.SlideDto;

import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.SlideMapper;
import com.alkemy.ong.model.Organization;
import com.alkemy.ong.model.Slide;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.service.OrganizationService;
import com.alkemy.ong.service.SlideService;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SlideServiceImpl implements SlideService {

	private final SlideRepository repository;
	private final SlideMapper mapper;
	private final AmazonS3ServiceImpl amazonService;
	private final OrganizationService orgService;

	@Transactional(readOnly = true)
	@Override
	public SlideDetailDto getSlideById(Long id) {
		return repository.findById(id).map(mapper::toSlideDetailDto).orElseThrow(() -> new NotFoundException("Id not found: " + id));
	}

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

	@Transactional(readOnly = true)
	@Override
	public List<SlideDetailDto> getAllSlides() {
		List<Slide> slides = repository.findAll();
		List<SlideDetailDto> slidesDetailDto = mapper.toSlideDetailDto(slides);
		return slidesDetailDto;
	}

	@Transactional(readOnly = true)
	@Override
	public List<SlideDetailDto> getAllSlidesbyOrg(Long idOrg) {
		List<Slide> slides = repository.findByOrg(idOrg);
		List<SlideDetailDto> slidesDetailDto = mapper.toSlideDetailDto(slides);
		Collections.sort(slidesDetailDto, new Comparator<SlideDetailDto>() {
            @Override
            public int compare(SlideDetailDto p1, SlideDetailDto p2) {
                return p1.getOrder() - p2.getOrder();
            }
        });
		return slidesDetailDto;

	}

	@Transactional
	@Override
	public void updateSlides(SlideDto dto, Long id) {
		
		repository.findById(id).map(slide -> {
			slide.setImageUrl(amazonService.uploadImage64(dto.getImageB64()));
			slide.setOrder(dto.getOrder());
			slide.setOrganization(orgService.findById(dto.getOrganizationId()));
			slide.setText(dto.getText());
			
			if (dto.getOrder() == null) {
				Integer maxOrder = repository.findMaxOrder(dto.getOrganizationId());
				if (maxOrder == null) {
					slide.setOrder(1);
				} else {
					slide.setOrder(maxOrder + 1);
				}
			}
			
			return repository.save(slide);
		}).orElseThrow(()->{
			throw new NotFoundException("Slide not found.");
		});
		
	}

  @Override
	public void deleteSlide(Long id) {
		if (!repository.existsById(id)) {
			throw new NotFoundException("Id not found: " + id);
		}
		repository.deleteById(id);
	}
	
}
