package com.alkemy.ong.controller.v1;

import static com.alkemy.ong.controller.ControllerConstants.V_1_SLIDES;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.ong.dto.SlideDetailDto;
import com.alkemy.ong.service.SlideService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(V_1_SLIDES)
@RequiredArgsConstructor
public class SlideController {
    private final SlideService service;
    
    @GetMapping("/{id}")
    public ResponseEntity<SlideDetailDto>getSlideById(@PathVariable Long id){
    	SlideDetailDto detailSlide = service.getSlideById(id);
        return ResponseEntity.ok().body(detailSlide);
    }
    
    
}
	