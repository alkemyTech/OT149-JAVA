package com.alkemy.ong.controller.v1;

import com.alkemy.ong.dto.NewDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.alkemy.ong.dto.NewDetailDto;
import com.alkemy.ong.service.NewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

import static com.alkemy.ong.controller.ControllerConstants.V_1_NEWS;

@RestController
@RequestMapping(V_1_NEWS)
@RequiredArgsConstructor
public class NewController {
    private final NewService service;

    @GetMapping("/{id}")
    public ResponseEntity<NewDetailDto>getNewById(@PathVariable Long id){
        NewDetailDto detailNew = service.getNewById(id);
        return ResponseEntity.ok().body(detailNew);
    }
  
  @PutMapping("/{id}")
	public ResponseEntity<NewDetailDto> saveOrUpdateNews(@RequestBody NewDto news, @PathVariable(value="id") Long id ){

		return new ResponseEntity<NewDetailDto>(service.addNews(news,id),HttpStatus.CREATED);
	}

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<Void> createNew (UriComponentsBuilder uriComponentsBuilder, @Valid @RequestBody NewDto dto){
        final long newId = service.createNew(dto);
        UriComponents uriComponents = uriComponentsBuilder.path("/{id}").buildAndExpand(newId);
        return ResponseEntity.created(uriComponents.toUri()).build();

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteNew(@PathVariable Long id) {
        service.deleteNew(id);
    }

}
