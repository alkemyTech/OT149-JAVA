package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.dto.CommentDtoList;
import com.alkemy.ong.mapper.CommentMapper;
import com.alkemy.ong.model.Comment;
import com.alkemy.ong.repository.CommentRepository;
import com.alkemy.ong.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentMapper mapper;
	@Autowired
	private CommentRepository repository;

	@Transactional(readOnly = true)
	@Override
	public List<CommentDtoList> getAllComment() {
		List<Comment> commentList = repository.findAll();
		return mapper.toCommentDtoList(commentList);
	}

	@Override
	public Long saveComment(CommentDto dto) {
			Comment comment = mapper.toComment(dto);
			repository.save(comment);
			return comment.getId();
	}
}