package com.alkemy.ong.controller.v1;

import com.alkemy.ong.service.CommentService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

import static com.alkemy.ong.controller.ControllerConstants.V_1_COMMENTS;

@RestController
@RequiredArgsConstructor
@RequestMapping(V_1_COMMENTS)
public class CommentController {

	private final CommentService service;

	public CommentController(CommentService service) {
		this.service = service;
	}

	@PutMapping("/{id}")
	public CommentDto update(
			@PathVariable Long id,
			@Valid @RequestBody CommentDto dto){
		return service.commentPut(id, dto);
	}

}
