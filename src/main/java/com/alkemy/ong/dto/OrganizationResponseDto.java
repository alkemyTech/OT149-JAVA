package com.alkemy.ong.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationResponseDto {

    private String name;
    private String images;
    private String address;
    private int phone;
    private String facebook;
    private String instagram;
    private String linkedin;
}
