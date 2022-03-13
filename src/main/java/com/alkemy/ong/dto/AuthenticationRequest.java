package com.alkemy.ong.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    
    @Email(message = "This field must be an email")
    @NotBlank(message = "The email must not be empty")
    private String email;

    @NotBlank(message ="The password must not be empty")
    private String password;
}
