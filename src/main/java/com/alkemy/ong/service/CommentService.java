package com.alkemy.ong.service;

import com.alkemy.ong.dto.CommentDto;

import java.util.List;

public interface CommentService {

	void commentPut(Long id, CommentDto dto);
	Long saveComment(CommentDto dto);
	List<CommentDto> getAllCommentsByPost(Long id);
}
