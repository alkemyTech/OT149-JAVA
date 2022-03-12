package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.mapper.CommentMapper;
import com.alkemy.ong.model.Comment;
import com.alkemy.ong.repository.CommentRepository;
import com.alkemy.ong.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentMapper mapper;
	@Autowired
	private CommentRepository repository;

	@Transactional
	@Override
	public CommentDto saveComment(CommentDto dto) {
		Comment comment = mapper.toComment(dto);
		Comment savedComment = repository.save(comment);
		return mapper.toCommentDto(savedComment);
	}
}
