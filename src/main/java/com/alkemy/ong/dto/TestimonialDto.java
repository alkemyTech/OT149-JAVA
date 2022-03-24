package com.alkemy.ong.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestimonialDto {

	@NotEmpty(message = "The name field cannot be empty.")
	private String name;
	private String image;
	@NotEmpty(message = "The content field cannot be empty.")
	private String content;
}
