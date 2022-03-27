package com.alkemy.ong.controller.v1;

import com.alkemy.ong.controller.ControllerConstants;
import com.alkemy.ong.dto.TestimonialPagedList;
import com.alkemy.ong.exception.ErrorDetails;
import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.service.TestimonialService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

import static com.alkemy.ong.controller.ControllerConstants.V_1_TESTIMONIAL;

@RestController
@RequestMapping(V_1_TESTIMONIAL)
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class TestimonialController {

	private final TestimonialService service;

	@Operation(summary = "Update testimonial")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Update testimonial", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = TestimonialDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid field", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)) }),
			@ApiResponse(responseCode = "403", description = "Invalid token or token expired | Accessing with invalid role", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)) }),
			@ApiResponse(responseCode = "404", description = "Invalid id supplied", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)) })

	})
	@PutMapping("/{id}")
	public TestimonialDto update(@PathVariable Long id, @Valid @RequestBody TestimonialDto dto) {
		return service.testimonialPut(id, dto);
	}

	@Operation(summary = "Add a new testimonial to the database")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Create testimonial"),
			@ApiResponse(responseCode = "400", description = "Invalid field", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)) }),
			@ApiResponse(responseCode = "403", description = "Invalid token or token expired | Accessing with invalid role", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)) }) })
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Void> createTestimonial(UriComponentsBuilder uriComponentsBuilder,
			@Valid @RequestBody TestimonialDto dto) {
		final long testimonialId = service.saveTestimonial(dto);

		UriComponents uriComponents = uriComponentsBuilder.path(V_1_TESTIMONIAL + "/{id}")
				.buildAndExpand(testimonialId);

		return ResponseEntity.created(uriComponents.toUri()).build();

	}

	@Operation(summary = "Get a paginated list of testimonials")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retrieve a paginated list of testimonials", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = TestimonialPagedList.class)) }),
			@ApiResponse(responseCode = "403", description = "Invalid token or token expired | Accessing with invalid role", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)) }) })

	@GetMapping
	public ResponseEntity<TestimonialPagedList> list(
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {

		if (pageNumber == null || pageNumber < 0) {
			pageNumber = ControllerConstants.DEFAULT_PAGE_NUMBER;
		}

		if (pageSize == null || pageSize < 1) {
			pageSize = ControllerConstants.DEFAULT_PAGE_SIZE;
		}

		return ResponseEntity.ok(service.pagedList(PageRequest.of(pageNumber, pageSize)));
	}

	/**
	 * This endpoint allows the administrator to delete a testimonial
	 *
	 * @param id The testimonial's id to be deleted
	 * @return Void
	 */

	@Operation(summary = "Delete a testimonial by id")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Delete testimonial", content = @Content),
			@ApiResponse(responseCode = "403", description = "Invalid token or token expired | Accessing with invalid role", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)) }),
			@ApiResponse(responseCode = "404", description = "Testimonial not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)) }),

	})
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTestimonial(@PathVariable Long id) {
		this.service.deleteTestimonial(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
