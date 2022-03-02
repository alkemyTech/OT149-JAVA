package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.NewDetailDto;
import com.alkemy.ong.exception.NewNotFoundException;
import com.alkemy.ong.mapper.NewMapper;
import com.alkemy.ong.model.New;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.service.NewService;
import org.springframework.stereotype.Service;

@Service
public class NewServiceImpl implements NewService {

    private NewsRepository repository;
    private NewMapper mapper;

    public NewDetailDto getNewById (Long id){
        return repository.findById(id).map(newModel ->{
            return mapper.toNewDetailDto(newModel);
        }).orElseThrow(()->{
            throw new NewNotFoundException();
        });
    }
}
