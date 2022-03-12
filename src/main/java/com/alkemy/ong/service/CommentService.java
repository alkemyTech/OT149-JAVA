package com.alkemy.ong.service;

import com.alkemy.ong.dto.CommentDto;

import java.util.Optional;

public interface CommentService {

	Optional<CommentDto> saveComment(CommentDto dto);
}
