package com.alkemy.ong.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {
    @NotBlank(message = "Obligatory field.")
    @Pattern(regexp = "[a-zA-Z\\s]+", message = "The name field must contain only text without numbers")
    private String name;
    private String description;
    private String image;
}
