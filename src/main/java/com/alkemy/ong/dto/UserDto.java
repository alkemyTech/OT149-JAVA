package com.alkemy.ong.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    @NotEmpty(message = "The name field cannot be empty")
    private String firstName;

    @NotEmpty(message = "The last name field cannot be empty")
    private String lastName;

    @NotEmpty(message = "The email field cannot be empty")
    @Email(message = "An email is required")
    private String email;

    @NotEmpty( message = "The password field cannot be empty")
    private String password;

    private String photo;

}
