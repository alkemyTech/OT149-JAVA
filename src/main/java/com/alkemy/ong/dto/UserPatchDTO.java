package com.alkemy.ong.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserPatchDTO {
    @NotEmpty(message = "The field cannot be empty.")
    private String firstName;
    @NotEmpty(message = "The field cannot be empty.")
    private String lastName;
    @NotEmpty(message = "The field cannot be empty.")
    private String photo;
}
