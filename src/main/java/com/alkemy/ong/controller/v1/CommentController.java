package com.alkemy.ong.controller.v1;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

import java.util.Optional;

import static com.alkemy.ong.controller.ControllerConstants.V_1_COMMENTS;

@RestController
@RequiredArgsConstructor
@RequestMapping(V_1_COMMENTS)
public class CommentController {

	private final CommentService service;

	@PostMapping()
	public ResponseEntity<Void> createComment(UriComponentsBuilder uriComponentsBuilder,
	                                          @Valid @RequestBody CommentDto dto) {

		final long id = service.saveComment(dto);
		UriComponents uriComponents = uriComponentsBuilder.path(V_1_COMMENTS + "/{id}").buildAndExpand(id);
		return ResponseEntity.created(uriComponents.toUri()).build();
	}
}