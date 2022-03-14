package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.model.Comment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

	Comment toComment(CommentDto dto);
	CommentDto toCommentDto(Comment comment);
	List<CommentDto> toListCommentDto(List<Comment> listComment);
}
