package com.alkemy.ong.controller.v1;

import com.alkemy.ong.exception.ErrorDetails;
import com.alkemy.ong.service.CommentService;
import com.alkemy.ong.dto.CommentDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
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

import java.util.List;

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
			@Valid @RequestBody CommentDto dto) {
		service.commentPut(id, dto);
	}

	@PostMapping()
	public ResponseEntity<Void> createComment(UriComponentsBuilder uriComponentsBuilder,
	                                          @Valid @RequestBody CommentDto dto) {
		final long id = service.saveComment(dto);
		UriComponents uriComponents = uriComponentsBuilder.path(V_1_COMMENTS + "/{id}").buildAndExpand(id);
		return ResponseEntity.created(uriComponents.toUri()).build();
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping
	public ResponseEntity<List<CommentDto>> getAllComments() {
		List<CommentDto> commentDtoLists = service.getAllComment();
		return ResponseEntity.status(HttpStatus.OK).body(commentDtoLists);
	}

	@Operation(summary = "Delete a comment by its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Comment deleted",
					content = @Content),
			@ApiResponse(responseCode = "403", description = "Without permission",
					content = @Content),
			@ApiResponse(responseCode = "404", description = "Comment not found",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = ErrorDetails.class)) })
	})
	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Comment deleted.")
	public void deleteComment(@PathVariable Long id){
		service.deleteComment(id);
	}

	@Operation(summary = "Get comments by post.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Success",
					content = @Content),
	})
	@GetMapping("/posts/{id}")
	public ResponseEntity<List<CommentDto>> getAllCommentsByPost(@PathVariable Long id){
		List<CommentDto> commentList = service.getAllCommentsByPost(id);
		return ResponseEntity.ok().body(commentList);
	}
}