package com.alkemy.ong.controller.v1;

import com.alkemy.ong.exception.ErrorDetails;
import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.service.AmazonS3Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
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
@SecurityRequirement(name = "bearerAuth")
public class ImageUploadController {

	private final AmazonS3Service service;


	@Operation(summary = "Add a new image to the database")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Create contact",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid field",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = ErrorDetails.class)) }),
			@ApiResponse(responseCode = "403", description = "Invalid token or token expired | Accessing with invalid role",
					content = {@Content(mediaType = "application/json",
							schema = @Schema(implementation = ErrorDetails.class))})
	})
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public String uploadFile(@RequestPart(value = "file") MultipartFile file) {
		return service.uploadImage(file);
	}
}
