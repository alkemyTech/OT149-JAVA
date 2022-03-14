package com.alkemy.ong.service;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.dto.CommentDtoList;
import java.util.List;

public interface CommentService {

	List<CommentDtoList> getAllComment();
	Long saveComment(CommentDto dto);
}
