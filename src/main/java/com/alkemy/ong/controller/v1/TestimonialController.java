package com.alkemy.ong.controller.v1;

import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.exception.TestimonialNotFoundException;
import com.alkemy.ong.service.TestimonialService;
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

	@PutMapping("/{id}")
	public TestimonialDto update(
			@PathVariable Long id,
			@Valid @RequestBody TestimonialDto dto) throws TestimonialNotFoundException {
		return service.testimonialPut(id, dto);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createTestimonial(@Valid @RequestBody TestimonialDto dto){
		service.saveTestimonial(dto);
	}
}