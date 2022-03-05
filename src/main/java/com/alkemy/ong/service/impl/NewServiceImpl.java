package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.NewDto;
import com.alkemy.ong.dto.NewResponseDto;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.NewResponseMapper;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.service.NewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewServiceImpl implements NewService {

    private final NewsRepository newsRepository;
    private final NewResponseMapper newsResponseMapper;

    @Override
    public NewResponseDto addNews(NewDto newDto, Long id) {

        return newsRepository.findById(id)
                .map(tempNew -> {

                    tempNew.setId(id);
                    tempNew.setName(newDto.getName());
                    tempNew.setContent(newDto.getContent());
                    tempNew.setImage(newDto.getImage());
                    tempNew.setCategoryId(newDto.getCategoryId());

                    return newsResponseMapper.toNewsResponseDto(newsRepository.save(tempNew));
                }).orElseThrow(() -> new NotFoundException("News id not found - " + id));
    }


}
