package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.NewDetailDto;
import com.alkemy.ong.mapper.NewMapper;
import com.alkemy.ong.service.NewService;
import org.springframework.stereotype.Service;

@Service
public class NewServiceImp implements NewService {
    private NewRepository repository;
    private NewMapper mapper;
    public NewDetailDto getNewById (Long id){
        New newModel = repository.getById(id);
        NewDetailDto result = mapper.toNewDetailDto(newModel);
        return result;
    }
}
