package com.alkemy.ong.dto;

import com.alkemy.ong.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewDto {
    @NotEmpty(message = "The name field cannot be empty")
    private String name;
    @NotEmpty(message = "The content field cannot be empty")
    private String content;
    @NotEmpty(message = "The image field cannot be empty")
    private String image;

    private Category categoryId;

    private String type = "news";
}
