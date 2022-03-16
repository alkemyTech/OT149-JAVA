package com.alkemy.ong.service;

import com.alkemy.ong.dto.CommentDto;

import java.util.List;

public interface CommentService {

	List<CommentDto> getAllComment();
	void commentPut(Long id, CommentDto dto);
	Long saveComment(CommentDto dto);
	void deleteComment(Long id);
	List<CommentDto> getAllCommentsByPost(Long id);
}
