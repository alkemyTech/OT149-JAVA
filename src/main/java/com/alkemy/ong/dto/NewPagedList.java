package com.alkemy.ong.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@Getter
@Setter
public class NewPagedList extends PageImpl<NewDto> {
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public NewPagedList(@JsonProperty("content") List<NewDto> content,
                        @JsonProperty("number") int number,
                        @JsonProperty("size") int size,
                        @JsonProperty("totalElements") Long totalElements,
                        @JsonProperty("pageable")JsonNode pageable,
                        @JsonProperty("last") boolean last,
                        @JsonProperty("totalPages") int totalPages,
                        @JsonProperty("sort") JsonNode sort,
                        @JsonProperty("first") boolean first,
                        @JsonProperty("numberOfElements") int numberOfElements){
        super(content, PageRequest.of(number, size), totalElements);
    }
    public NewPagedList(List<NewDto> content, Pageable pageable, long total){
        super(content, pageable, total);
    }

    private URI nextUri;
    @Positive
    private URI backUri;
}
