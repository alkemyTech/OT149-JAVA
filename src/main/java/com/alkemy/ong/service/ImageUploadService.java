package com.alkemy.ong.service;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface ImageUploadService {

	File convertMultiPartToFile(MultipartFile file) throws IOException;

	String generateFileName(MultipartFile multiPart);

	void uploadFileTos3bucket(String fileName, File file);

	@Secured("ROLE_ADMIN")
	String uploadImage(MultipartFile multipartFile) throws FileUploadException;
}
