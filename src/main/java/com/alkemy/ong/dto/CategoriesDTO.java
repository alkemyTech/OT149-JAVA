package com.alkemy.ong.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
public class CategoriesDTO {
    private Long id;
    @NotEmpty(message = "The name field cannot be empty")
    private String name;
    private String description;
    private String image;
    private LocalDate createdDate;
    private LocalDate modifiedDate;
    private boolean isActive = Boolean.TRUE;
}
