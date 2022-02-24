package com.alkemy.ong.dto;

import com.alkemy.ong.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewDetailDto {
    private String name;
    private String content;
    private String image;
    private Category categoryId;
}
