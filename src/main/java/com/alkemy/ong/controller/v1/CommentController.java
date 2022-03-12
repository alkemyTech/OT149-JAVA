package com.alkemy.ong.controller.v1;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.dto.CommentDtoList;
import com.alkemy.ong.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.List;
import java.util.Optional;

import static com.alkemy.ong.controller.ControllerConstants.V_1_COMMENTS;

@RestController
@RequiredArgsConstructor
@RequestMapping(V_1_COMMENTS)
public class CommentController {

	private final CommentService service;

	@PostMapping
	public ResponseEntity<Optional<CommentDto>> createComment(@Valid @RequestBody CommentDto dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.saveComment(dto));
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping
	public ResponseEntity<List<CommentDtoList>> getAllComments() {
		List<CommentDtoList> commentDtoLists = service.getAllComment();
		return ResponseEntity.status(HttpStatus.OK).body(commentDtoLists);
	}
}