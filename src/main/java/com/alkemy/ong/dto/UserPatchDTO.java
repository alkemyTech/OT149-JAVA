package com.alkemy.ong.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserPatchDTO {
    @NotEmpty(message = "The field cannot be empty.")
    private String firstName;
    @NotEmpty(message = "The field cannot be empty.")
    private String lastName;
    @NotEmpty(message = "The field cannot be empty.")
    private String photo;
}
