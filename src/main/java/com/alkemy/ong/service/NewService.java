package com.alkemy.ong.service;

 import com.alkemy.ong.dto.NewDetailDto;
 import com.alkemy.ong.dto.NewDto;
 import com.alkemy.ong.dto.NewPagedList;
 import org.springframework.data.domain.PageRequest;

public interface NewService {
    NewDetailDto getNewById(Long id);
    NewDetailDto addNews(NewDto news, Long id);
    long createNew (NewDto news);
    NewPagedList pagedList(PageRequest pageRequest);
}
