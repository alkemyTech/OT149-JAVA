package com.alkemy.ong.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactDto {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "The name must not be empty")
    private String name;

    @NotBlank(message = "The phone must not be empty")
    @Pattern(regexp = "^[(]{1}[0-9]+[)]{1}[0-9]+$",message = "This field must contain only numbers with the area code in parentheses like this (xxx)xxxxxxx")
    private String phone;

    @NotBlank(message = "The email must not be empty")
    @Email(message = "This field must be an email")
    private String email;

    @Size(max = 1000, message = "The message must be no longer than 1000 characters")
    @NotBlank(message = "The message must not be empty")
    private String message;

}
