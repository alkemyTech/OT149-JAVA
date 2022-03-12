package com.alkemy.ong.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.multipart.MultipartFile;

public interface AmazonS3Service {

	@Secured("ROLE_ADMIN")
	String uploadImage(MultipartFile multipartFile);
	
	String uploadImage64(String b64) throws FileUploadException;
	
	File b64ToFile(String b64) throws FileNotFoundException, IOException;
	
	String generateFileName(File file);

}
