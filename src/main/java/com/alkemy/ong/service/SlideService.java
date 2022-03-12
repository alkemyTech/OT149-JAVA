package com.alkemy.ong.service;

import org.apache.tomcat.util.http.fileupload.FileUploadException;

import com.alkemy.ong.dto.SlideDto;

public interface SlideService {

	SlideDto saveSlide(SlideDto dto) throws FileUploadException;
	
}
