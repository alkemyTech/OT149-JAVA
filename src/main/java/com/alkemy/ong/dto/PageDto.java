package com.alkemy.ong.dto;

import lombok.Data;

import java.util.List;

@Data
public class PageDto<T> {

	private Integer totalPages;
	private String nextPage;
	private String previousPage;
	private List<T> list;
}
