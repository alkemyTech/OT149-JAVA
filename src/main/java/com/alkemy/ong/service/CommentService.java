package com.alkemy.ong.service;

import com.alkemy.ong.dto.CommentDto;

public interface CommentService {

	CommentDto commentPut(Long id, CommentDto dto);
}
