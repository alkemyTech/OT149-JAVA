package com.alkemy.ong.service;

 import com.alkemy.ong.dto.NewDetailDto;
 import com.alkemy.ong.dto.NewDto;

public interface NewService {
    NewDetailDto getNewById(Long id);
    NewDetailDto addNews(NewDto news, Long id);
    void createNew (NewDto news);

}
