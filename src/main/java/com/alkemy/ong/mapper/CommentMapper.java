package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper
public interface CommentMapper {

	Comment toComment(CommentDto dto);
	CommentDto toCommentDto(Comment comment);
	List<CommentDto> toListCommentDto(List<Comment> listComment);

	@Mapping(target = "usersId", expression = "java(null)")
	@Mapping(target = "newsId", expression = "java(null)")
	@Named("reduced")
	List<CommentDto> toCommentDtoReduced(List<Comment> commentList);
}
