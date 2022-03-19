package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.exception.CommentNotFoundException;
import com.alkemy.ong.exception.NewNotFoundException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.exception.UserNotFoundException;
import com.alkemy.ong.model.New;
import com.alkemy.ong.model.User;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.alkemy.ong.mapper.CommentMapper;
import com.alkemy.ong.model.Comment;
import com.alkemy.ong.repository.CommentRepository;
import com.alkemy.ong.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper mapper;
    @Autowired
    private CommentRepository repository;
    @Autowired
    private UserRepository usersRepository;
    @Autowired
    private NewsRepository newRepository;

    @Transactional(readOnly = true)
    @Override
    public List<CommentDto> getAllComment() {
        List<Comment> commentList = repository.findAll();
        return mapper.toCommentDtoReduced(commentList);
    }

    @Transactional
    @Override
    public Long saveComment(CommentDto dto) {
        User user = usersRepository.findById(dto.getUsersId()).orElseThrow(UserNotFoundException::new);
        New news = newRepository.findById(dto.getNewsId()).orElseThrow(NewNotFoundException::new);
        Comment comment = mapper.toComment(dto);
        comment.setUser(user);
        comment.setNews(news);
        repository.save(comment);
        return comment.getId();
    }

	@Transactional
	@Override
	public void commentPut(Long id, CommentDto dto){
		repository.findById(id).map( comment -> {
			comment.setBody(dto.getBody());
			repository.save(comment);
			return mapper.toCommentDto(comment);
		}).orElseThrow(() -> {
			throw new NotFoundException("Comment not found");
		});
	}

    @Transactional(readOnly = true)
    @Override
    public List<CommentDto> getAllCommentsByPost(Long id) {
        List<Comment> commentList = repository.findByNewsId(id);
        return mapper.toListCommentDto(commentList);
    }

    @Transactional
    @Override
    public void deleteComment(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new CommentNotFoundException();
        }
        repository.deleteById(id);
    }
}
