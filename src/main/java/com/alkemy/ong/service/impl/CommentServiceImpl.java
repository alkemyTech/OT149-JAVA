package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.exception.NotFoundException;
import org.springframework.stereotype.Service;
import com.alkemy.ong.mapper.CommentMapper;
import com.alkemy.ong.model.Comment;
import com.alkemy.ong.repository.CommentRepository;
import com.alkemy.ong.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentMapper mapper;
	@Autowired
	private CommentRepository repository;

	@Transactional
	@Override
	public Long saveComment(CommentDto dto) {
			Comment comment = mapper.toComment(dto);
			repository.save(comment);
			return comment.getId();
    }

	@Override
	public CommentDto commentPut(Long id, CommentDto dto){
		return repository.findById(id).map( comment -> {
			comment.setBody(dto.getBody());
			return mapper.toCommentDto(comment);
		}).orElseThrow(() -> {
			throw new NotFoundException("Comment not found");
		});
	}
}
