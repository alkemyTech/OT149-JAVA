package com.alkemy.ong.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationPutDto {

    @NotBlank(message = "The field cannot be empty.")
    private String name;
    @NotBlank(message = "The field cannot be empty.")
    private String images;
    private String address;
    private int phone;
    @NotBlank(message = "Email cannot be empty.")
    @Email(message = "The field must be a Email.")
    private String email;
    @NotBlank(message = "welcomeText cannot be empty.")
    private  String welcomeText;
    private String aboutUsText;
}
