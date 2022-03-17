package com.alkemy.ong.service.impl;


import com.alkemy.ong.dto.NewDetailDto;
import com.alkemy.ong.dto.NewPagedList;
import com.alkemy.ong.exception.CategoryNotFoundException;
import com.alkemy.ong.exception.NewNotFoundException;
import com.alkemy.ong.mapper.NewMapper;
import com.alkemy.ong.model.New;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.service.NewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.alkemy.ong.dto.NewDto;
import com.alkemy.ong.exception.NotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewServiceImpl implements NewService {

    private final NewsRepository repository;
    private final NewMapper mapper;

    @Transactional(readOnly = true)
    @Override
    public NewDetailDto getNewById(Long id) {
        return repository.findById(id).map(newModel -> {
            return mapper.toNewDetailDto(newModel);
        }).orElseThrow(() -> {
            throw new NewNotFoundException();
        });
    }

    @Transactional
    @Override
    public NewDetailDto addNews(NewDto newDto, Long id) {

        return repository.findById(id).map(tempNew -> {

            tempNew.setId(id);
            tempNew.setName(newDto.getName());
            tempNew.setContent(newDto.getContent());
            tempNew.setImage(newDto.getImage());
            tempNew.setCategoryId(newDto.getCategoryId());

            return mapper.toNewDetailDto(repository.save(tempNew));
        }).orElseThrow(() -> new NotFoundException("News id not found - " + id));
    }

    @Transactional
    @Override
    public long createNew(NewDto dto) {
        New news = mapper.toNew(dto);
        repository.save(news);
        long newId = news.getId();
        return newId;
    }

    @Transactional(readOnly = true)
    @Override
    public NewPagedList pagedList(PageRequest pageRequest) {
        Page<New> pageNew = repository.findAll(pageRequest);
        final List<NewDto> list = pageNew.getContent().stream().map(mapper::toDto).collect(Collectors.toList());
        final PageRequest of = PageRequest.of(pageNew.getPageable().getPageNumber(), pageNew.getPageable().getPageSize());
        final long totalElements = pageNew.getTotalElements();
        return new NewPagedList(list, of, totalElements);
    }

    @Transactional
    @Override
    public void deleteNew(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new NewNotFoundException();
        }
        repository.deleteById(id);
    }
}
