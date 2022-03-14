package com.alkemy.ong.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.multipart.MultipartFile;

public interface AmazonS3Service {

	@Secured("ROLE_ADMIN")
	String uploadImage(MultipartFile multipartFile);
	
	@Secured("ROLE_ADMIN")
	String uploadImage64(String b64);
	
	File b64ToFile(String b64) throws FileNotFoundException, IOException;
	
	String generateFileName(File file);

}
