package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.dto.CommentDtoList;
import com.alkemy.ong.model.Comment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

	Comment toComment(CommentDto dto);
	CommentDto toCommentDto(Comment comment);
	List<CommentDtoList> toCommentDtoList(List<Comment> commentList);
}
