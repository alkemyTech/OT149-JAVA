package com.alkemy.ong.controller.v1;

import com.alkemy.ong.dto.SlideDto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.alkemy.ong.service.SlideService;

import lombok.RequiredArgsConstructor;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

import static com.alkemy.ong.controller.ControllerConstants.V_1_SLIDES;

@RestController
@RequestMapping(V_1_SLIDES)
@RequiredArgsConstructor
public class SlideController {
    private final SlideService service;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<SlideDto> createSlide (@Valid @RequestBody SlideDto dto) throws FileUploadException{
        final SlideDto slideDto = service.saveSlide(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(slideDto);

    }

}
	