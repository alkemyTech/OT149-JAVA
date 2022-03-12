package com.alkemy.ong.controller.v1;

import com.alkemy.ong.exception.ErrorDetails;
import com.alkemy.ong.dto.NewDetailDto;
import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.exception.TestimonialNotFoundException;
import com.alkemy.ong.service.TestimonialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import javax.validation.Valid;

import static com.alkemy.ong.controller.ControllerConstants.V_1_TESTIMONIAL;

@RestController
@RequestMapping(V_1_TESTIMONIAL)
@RequiredArgsConstructor
public class TestimonialController {

	@Autowired
	private final TestimonialService service;


	@Operation(summary = "Update testimonial")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Update testimonial",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = TestimonialDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid field",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = ErrorDetails.class)) }),
			@ApiResponse(responseCode = "404", description = "Invalid id supplied",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = ErrorDetails.class)) })
	})
	@PutMapping("/{id}")
	public TestimonialDto update(
			@PathVariable Long id,
			@Valid @RequestBody TestimonialDto dto){
		return service.testimonialPut(id, dto);
	}

	@Operation(summary = "Add a new testimonial to the database")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Create testimonial"),
			@ApiResponse(responseCode = "400", description = "Invalid field",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = ErrorDetails.class)) })
	})
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createTestimonial(@Valid @RequestBody TestimonialDto dto){
		service.saveTestimonial(dto);
	}
}