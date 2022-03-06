package com.alkemy.ong.service.impl;


import com.alkemy.ong.dto.NewDetailDto;
import com.alkemy.ong.exception.NewNotFoundException;
import com.alkemy.ong.mapper.NewMapper;
import com.alkemy.ong.model.New;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.service.NewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.alkemy.ong.dto.NewDto;
import com.alkemy.ong.exception.NotFoundException;

@Service
@RequiredArgsConstructor
public class NewServiceImpl implements NewService {

    private final NewsRepository repository;
    private final NewMapper mapper;

    public NewDetailDto getNewById (Long id){
        return repository.findById(id).map(newModel ->{
            return mapper.toNewDetailDto(newModel);
        }).orElseThrow(()->{
            throw new NewNotFoundException();
        });
    }
  
  
  @Override
    public NewDetailDto addNews(NewDto newDto, Long id) {

        return repository.findById(id)
                .map(tempNew -> {

                    tempNew.setId(id);
                    tempNew.setName(newDto.getName());
                    tempNew.setContent(newDto.getContent());
                    tempNew.setImage(newDto.getImage());
                    tempNew.setCategoryId(newDto.getCategoryId());

                    return mapper.toNewDetailDto(repository.save(tempNew));
                }).orElseThrow(() -> new NotFoundException("News id not found - " + id));
    }

    public void createNew(NewDto dto){
        New news = mapper.toNew(dto);
        repository.save(news);
    }
}
