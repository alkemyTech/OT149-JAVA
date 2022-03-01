package com.alkemy.ong.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActivityDto implements Serializable {

    @NotBlank(message = "The name field can not be empty")
    private String name;
    @NotBlank(message = "The text field can not be empty")
    private String text;
    @NotBlank(message = "The image field can not be empty")
    private String image;
}
