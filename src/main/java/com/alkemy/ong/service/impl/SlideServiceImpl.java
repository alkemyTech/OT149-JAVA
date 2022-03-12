package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.exception.NullFileException;
import com.alkemy.ong.mapper.SlideMapper;
import com.alkemy.ong.model.Member;
import com.alkemy.ong.model.Organization;
import com.alkemy.ong.model.Slide;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.service.OrganizationService;
import com.alkemy.ong.service.SlideService;
import com.amazonaws.services.s3.model.ObjectMetadata;

import lombok.RequiredArgsConstructor;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class SlideServiceImpl implements SlideService {

	private final SlideRepository repository;
	private final SlideMapper mapper;
	private final AmazonS3ServiceImpl amazonService;
	private final Base64 base64;
	private final OrganizationService orgService;

	@Transactional
	@Override
	public SlideDto saveSlide(SlideDto dto) throws FileUploadException {

		String fileUrl = amazonService.uploadImage64(dto.getImageB64());

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
		SlideDto dtoSaved = mapper.toSlideDto(saved);
		
		return dtoSaved;
	}

}
