package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl {

	@Override
	public CommentDto commentPut(Long id, CommentDto dto){
		return commentRepository.findById(id).map( comment -> {
			comment.setBody(dto.getBody());
			return mapper.toDto(comment);
		}).orElseThrow(NotFoundException::new);

	}
}
