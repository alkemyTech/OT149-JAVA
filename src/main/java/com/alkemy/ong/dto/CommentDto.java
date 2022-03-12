package com.alkemy.ong.dto;

import com.alkemy.ong.model.New;
import com.alkemy.ong.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

	@NotBlank(message = "Obligatory field.")
	private String body;
	@Column(nullable = false)
	private Long usersId;
	@Column(nullable = false)
	private Long newsId;
}
