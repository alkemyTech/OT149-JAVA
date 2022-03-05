package com.alkemy.ong.service;


import com.alkemy.ong.dto.NewDto;
import com.alkemy.ong.dto.NewResponseDto;

public interface NewService {

    NewResponseDto addNews(NewDto news, Long id);
}
