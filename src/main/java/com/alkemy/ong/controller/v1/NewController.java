package com.alkemy.ong.controller.v1;

import com.alkemy.ong.dto.NewDto;
import com.alkemy.ong.dto.NewResponseDto;
import com.alkemy.ong.service.NewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.alkemy.ong.controller.ControllerConstants.V_1_NEWS;

@RestController
@RequiredArgsConstructor
@RequestMapping(V_1_NEWS)
public class NewController {

    private final NewService newsService;

    @PutMapping("/{id}")
	public ResponseEntity<NewResponseDto> saveOrUpdateNews(@RequestBody NewDto news, @PathVariable(value="id") Long id ){

		return new ResponseEntity<NewResponseDto>(newsService.addNews(news,id),HttpStatus.CREATED);
	}

}
