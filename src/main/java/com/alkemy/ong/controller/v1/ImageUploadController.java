package com.alkemy.ong.controller.v1;

import com.alkemy.ong.service.AmazonS3Service;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static com.alkemy.ong.controller.ControllerConstants.V_1_IMAGES_UPLOAD;

@RestController
@RequestMapping(V_1_IMAGES_UPLOAD)
@RequiredArgsConstructor
public class ImageUploadController {

	private final AmazonS3Service service;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public String uploadFile(@RequestPart(value = "file") MultipartFile file) throws FileUploadException {
		return service.uploadImage(file);
	}
}
