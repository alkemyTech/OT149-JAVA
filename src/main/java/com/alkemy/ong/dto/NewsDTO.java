package com.alkemy.ong.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
public class NewsDTO {
    private Long id;
    @NotEmpty(message = "The name field cannot be empty")
    private String name;
    @NotEmpty(message = "The content field cannot be empty")
    private String content;
    @NotEmpty(message = "The image field cannot be empty")
    private String image;
    private LocalDate createdDate;
    private LocalDate modifiedDate;
    private boolean isActive = Boolean.TRUE;
}
