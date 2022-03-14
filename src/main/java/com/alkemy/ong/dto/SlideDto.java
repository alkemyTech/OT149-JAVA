package com.alkemy.ong.dto;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SlideDto {
    @NotEmpty(message = "The image field cannot be empty")
    private String imageB64;
    @NotEmpty(message = "The text field cannot be empty")
    private String text;
    @NotEmpty(message = "The order field cannot be empty")
    private Integer order;

    private Long organizationId;
}
	