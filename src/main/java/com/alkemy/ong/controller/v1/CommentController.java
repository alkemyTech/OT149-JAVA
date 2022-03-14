package com.alkemy.ong.controller.v1;

import com.alkemy.ong.service.CommentService;
import com.alkemy.ong.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

import static com.alkemy.ong.controller.ControllerConstants.V_1_COMMENTS;

@RestController
@RequiredArgsConstructor
@RequestMapping(V_1_COMMENTS)
public class CommentController {

	private final CommentService service;

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(
			@PathVariable Long id,
			@Valid @RequestBody CommentDto dto){
		service.commentPut(id, dto);
	}

	@PostMapping()
	public ResponseEntity<Void> createComment(UriComponentsBuilder uriComponentsBuilder,
	                                          @Valid @RequestBody CommentDto dto) {

		final long id = service.saveComment(dto);
		UriComponents uriComponents = uriComponentsBuilder.path(V_1_COMMENTS + "/{id}").buildAndExpand(id);
		return ResponseEntity.created(uriComponents.toUri()).build();
	}
}