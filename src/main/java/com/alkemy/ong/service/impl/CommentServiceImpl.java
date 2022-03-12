package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.dto.CommentDtoList;
import com.alkemy.ong.mapper.CommentMapper;
import com.alkemy.ong.model.Comment;
import com.alkemy.ong.repository.CommentRepository;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
	public Optional<CommentDto> saveComment(CommentDto dto) {
		if ( newsRepository.existsById(dto.getNewsId()) &&
		userRepository.existsById(dto.getUsersId())) {
			Comment comment = mapper.toComment(dto);
			Comment savedComment = repository.save(comment);
			CommentDto savedCommentDto = mapper.toCommentDto(savedComment);
			return Optional.of(savedCommentDto);
		} else {
			return Optional.empty();
		}

	}

	@Transactional(readOnly = true)
	@Override
	public List<CommentDtoList> getAllComment() {
		List<Comment> commentList = repository.findAll();
		return mapper.toCommentDtoList(commentList);
	}
}
