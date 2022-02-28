package com.alkemy.ong.service;


import com.alkemy.ong.dto.NewsResponseDto;
import com.alkemy.ong.model.New;

public interface NewsService {

    NewsResponseDto addNews(New news, Long id);

    String deleteNews(Long id);
}
