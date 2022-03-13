package com.alkemy.ong.controller.v1;

import com.alkemy.ong.dto.SlideDetailDto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.alkemy.ong.service.SlideService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.alkemy.ong.controller.ControllerConstants.V_1_SLIDES;

import java.util.List;

@RestController
@RequestMapping(V_1_SLIDES)
@RequiredArgsConstructor
public class SlideController {
    private final SlideService service;
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<SlideDetailDto>> getAllSlides(){
        List<SlideDetailDto> detailSlidesDto = service.getAllSlides();
        return ResponseEntity.ok().body(detailSlidesDto);
    }

}
	