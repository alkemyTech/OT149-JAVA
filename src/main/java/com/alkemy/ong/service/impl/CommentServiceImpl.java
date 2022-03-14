package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.exception.CommentNotFoundException;
import com.alkemy.ong.exception.NewNotFoundException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.CommentMapper;
import com.alkemy.ong.model.Comment;
import com.alkemy.ong.repository.CommentRepository;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.repository.UserRepository;
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
	@Autowired
	private NewsRepository newsRepository;
	@Autowired
	private UserRepository userRepository;

	@Transactional
	@Override
	public Long saveComment(CommentDto dto) {
			Comment comment = mapper.toComment(dto);
			repository.save(comment);
			return comment.getId();
	}

	public void deleteComment(Long id) {
		if (repository.findById(id).isEmpty()) {
			throw new CommentNotFoundException();
		}
		repository.deleteById(id);
	}
}
