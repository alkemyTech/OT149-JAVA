package com.alkemy.ong.service;

import com.alkemy.ong.dto.CommentDto;

public interface CommentService {

	void commentPut(Long id, CommentDto dto);
	Long saveComment(CommentDto dto);

}
