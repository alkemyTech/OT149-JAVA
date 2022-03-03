package com.alkemy.ong.controller.v1;

import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.service.TestimonialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.alkemy.ong.controller.ControllerConstants.V_1_TESTIMONIAL;

@RestController
@RequestMapping(V_1_TESTIMONIAL)
public class TestimonialController {

	@Autowired
	private TestimonialService service;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createTestimonial(@Valid @RequestBody TestimonialDto dto){
		service.saveTestimonial(dto);

	}
}