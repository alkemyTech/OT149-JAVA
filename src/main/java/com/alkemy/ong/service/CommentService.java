package com.alkemy.ong.service;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.dto.CommentDtoList;

import java.util.List;
import java.util.Optional;

public interface CommentService {

	Optional<CommentDto> saveComment(CommentDto dto);
	List<CommentDtoList> getAllComment();
}
