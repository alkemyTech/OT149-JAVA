package com.alkemy.ong.service;

import com.alkemy.ong.dto.CommentDto;

import java.util.List;

public interface CommentService {

	List<CommentDto> getAllComment();
	Long saveComment(CommentDto dto);
	List<CommentDto> getAllCommentsByPost(Long id);
}
