package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.NewsResponseDto;
import com.alkemy.ong.exception.NoContentException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.NewsResponseMapper;
import com.alkemy.ong.model.New;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NewsServiceImp implements NewsService {

    private final NewsRepository newsRepository;
    private final NewsResponseMapper newsResponseMapper;

    @Override
    public NewsResponseDto addNews(New news, Long id) {

        Optional<New> tempNew = newsRepository.findById(id);


        if(tempNew.isPresent()){
            news.setId(id);
            return newsResponseMapper.toNewsResponseDto(newsRepository.save(news));

        } else {
            throw new NotFoundException("News id not found - " + id);
        }
    }

    @Override
    public String deleteNews(Long id) {

        System.out.println(id);

        Optional<New> tempNew = newsRepository.findById(id);

        System.out.println(tempNew);

        if(tempNew.isPresent()){
            newsRepository.deleteById(id);
            return "Delete was successful";

        } else {
            throw new NoContentException();
        }
    }
}
