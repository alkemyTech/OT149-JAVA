package com.alkemy.ong.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

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
