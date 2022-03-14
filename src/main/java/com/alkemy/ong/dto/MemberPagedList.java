package com.alkemy.ong.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class MemberPagedList extends PageImpl<MemberDto> {

    public MemberPagedList(@JsonProperty("content") List<MemberDto> content, @JsonProperty("number") int number, @JsonProperty("size") int size, @JsonProperty("totalElements") Long totalElements, @JsonProperty("pageable") JsonNode pageable, @JsonProperty("last") boolean last, @JsonProperty("totalPages") int totalPages, @JsonProperty("sort") JsonNode sort, @JsonProperty("first") boolean first, @JsonProperty("numberOfElements") int numberOfElements) {
        super(content, PageRequest.of(number, size), totalElements);
    }

    public MemberPagedList(List<MemberDto> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }
}
