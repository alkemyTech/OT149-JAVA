package com.alkemy.ong.service.impl;

import com.alkemy.ong.exception.NullFileException;
import com.alkemy.ong.service.ImageUploadService;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

@Service
@Log4j2
public class ImageUploadServiceImpl implements ImageUploadService {

	private AmazonS3 s3client;

	@Value("${amazonProperties.bucketName}")
	private String bucketName;

	@Override
	public File convertMultiPartToFile(MultipartFile file) throws IOException {
		String filename = file.getOriginalFilename();
		if (filename == null) {
			throw new NullFileException("The file name must not be null");
		}
		File convFile = new File(filename);
		try (FileOutputStream fos = new FileOutputStream(convFile)) {
			fos.write(file.getBytes());
		}
		return convFile;
	}

	@Override
	public String generateFileName(MultipartFile multiPart) {
		String filename = multiPart.getOriginalFilename();
		if (filename == null) {
			throw new NullFileException("The file name must not be null");
		}
		return new Date().getTime() + "-" + filename.replace(" ", "_");
	}
	@Override
	public void uploadFileTos3bucket(String fileName, File file) {
		s3client.putObject(new PutObjectRequest(this.bucketName, fileName, file)
				.withCannedAcl(CannedAccessControlList.PublicRead));
	}

	@Override
	public String uploadImage(MultipartFile multipartFile) throws FileUploadException {
		File file = null;
		try {
			file = convertMultiPartToFile(multipartFile);
			String fileName = generateFileName(multipartFile);
			String fileUrl = s3client.getUrl(this.bucketName, fileName).toExternalForm();
			uploadFileTos3bucket(fileName, file);
			return fileUrl;

		} catch (IOException ex) {
			log.error("Error uploading file: ", ex);
			throw new FileUploadException("Error uploading file");
		} finally {
			if ((file != null) && !file.delete()) {
				log.error("Error deleting file");
			}
		}
	}
}
