package com.alkemy.ong.controller.v1;

import static com.alkemy.ong.controller.ControllerConstants.V_1_SLIDES;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.alkemy.ong.dto.SlideDetailDto;
import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.service.SlideService;

import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping(V_1_SLIDES)
@RequiredArgsConstructor
public class SlideController {
  
    private final SlideService service;
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<SlideDetailDto>getSlideById(@PathVariable Long id){
    	SlideDetailDto detailSlide = service.getSlideById(id);
        return ResponseEntity.ok().body(detailSlide);
    }
  
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<SlideDetailDto>> getAllSlides(){
        List<SlideDetailDto> detailSlidesDto = service.getAllSlides();
        return ResponseEntity.ok().body(detailSlidesDto);
    }

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping
	public ResponseEntity<Void> createSlide(UriComponentsBuilder uriComponentsBuilder, @Valid @RequestBody SlideDto dto) {
		final Long id = service.saveSlide(dto);
		UriComponents uriComponents = uriComponentsBuilder.path(V_1_SLIDES + "/{id}").buildAndExpand(id);
		return ResponseEntity.created(uriComponents.toUri()).build();
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteSlide(@PathVariable Long id) {
        service.deleteSlide(id);
    }
  
}
