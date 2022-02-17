package com.alkemy.ong.dto;

import com.alkemy.ong.entity.Categories;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    /*
    private List<Categories> categoryId = new ArrayList<>();
     */
}
