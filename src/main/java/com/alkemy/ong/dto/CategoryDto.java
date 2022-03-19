package com.alkemy.ong.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {
    @NotBlank(message = "Obligatory field.")
    @Pattern(regexp = "[a-zA-Z0-9\\s]+")
    private String name;
    private String description;
    private String image;
}
