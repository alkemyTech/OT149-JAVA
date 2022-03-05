package com.alkemy.ong.service;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.multipart.MultipartFile;

public interface AmazonS3Service {

	@Secured("ROLE_ADMIN")
	String uploadImage(MultipartFile multipartFile);

}
