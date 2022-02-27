package com.alkemy.ong.controller.v1;

import com.alkemy.ong.dto.NewsResponseDto;
import com.alkemy.ong.model.New;
import com.alkemy.ong.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.alkemy.ong.controller.ControllerConstants.V_1_NEWS;
import static com.alkemy.ong.controller.ControllerConstants.V_1_ORG;

@RestController
@RequiredArgsConstructor
@RequestMapping(V_1_NEWS)
public class NewsController {

    private final NewsService newsService;

    @PutMapping("/{id}")
	public ResponseEntity<NewsResponseDto> saveOrUpdateNews(@RequestBody New news, @PathVariable(value="id") Long id ){

		return new ResponseEntity<NewsResponseDto>(newsService.addNews(news,id),HttpStatus.CREATED);
	}

}
