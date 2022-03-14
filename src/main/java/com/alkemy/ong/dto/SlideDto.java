package com.alkemy.ong.dto;

import com.alkemy.ong.model.Organization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SlideDto {
    @NotEmpty(message = "The url´s image field cannot be empty")
    private String imageUrl;
    @NotEmpty(message = "The text field cannot be empty")
    private String text;
    @NotEmpty(message = "The order field cannot be empty")
    private Integer order;

    private Organization organizationId;
}
	