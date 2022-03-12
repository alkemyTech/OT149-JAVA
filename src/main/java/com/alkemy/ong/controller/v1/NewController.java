package com.alkemy.ong.controller.v1;

import com.alkemy.ong.controller.ControllerConstants;
import com.alkemy.ong.dto.*;
import com.alkemy.ong.exception.ErrorDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.alkemy.ong.service.NewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

import static com.alkemy.ong.controller.ControllerConstants.V_1_NEWS;

@RestController
@RequestMapping(V_1_NEWS)
@RequiredArgsConstructor
public class NewController {
    private final NewService service;

    @Operation(summary = "Get a news by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the new",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = NewDetailDto.class)) }),
            @ApiResponse(responseCode = "404", description = "New not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class)) })
    })
    @GetMapping("/{id}")
    public ResponseEntity<NewDetailDto>getNewById(@PathVariable Long id){
        NewDetailDto detailNew = service.getNewById(id);
        return ResponseEntity.ok().body(detailNew);
    }



    @Operation(summary = "Update news")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Update News",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = NewDetailDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid field",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class)) }),
            @ApiResponse(responseCode = "404", description = "Invalid id supplied",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class)) })
    })
  @PutMapping("/{id}")
	public ResponseEntity<NewDetailDto> saveOrUpdateNews(@RequestBody NewDto news, @PathVariable(value="id") Long id ){

		return new ResponseEntity<NewDetailDto>(service.addNews(news,id),HttpStatus.CREATED);
	}



    @Operation(summary = "Add a new news to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create news",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid field",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class)) })
    })
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<Void> createNew (UriComponentsBuilder uriComponentsBuilder, @Valid @RequestBody NewDto dto){
        final long newId = service.createNew(dto);
        UriComponents uriComponents = uriComponentsBuilder.path("/{id}").buildAndExpand(newId);
        return ResponseEntity.created(uriComponents.toUri()).build();

    }

    @GetMapping
    public ResponseEntity<NewPagedList> list(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                             @RequestParam(value = "pageSize", required = false) Integer pageSize){
        if (pageNumber == null || pageNumber < 0){
            pageNumber = ControllerConstants.DEFAULT_PAGE_NUMBER;
        }
        if (pageSize == null || pageSize < 1){
            pageSize = ControllerConstants.DEFAULT_PAGE_SIZE;
        }
        return ResponseEntity.ok(service.pagedList(PageRequest.of(pageNumber, pageSize)));
    }

}
