package com.alkemy.ong.controller.v1;


import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.dto.CategoryDetailDto;
import com.alkemy.ong.dto.CategoryListDto;
import com.alkemy.ong.dto.CategoryPutDto;
import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.exception.ErrorDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import com.alkemy.ong.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

import java.util.List;

import static com.alkemy.ong.controller.ControllerConstants.V_1_CATEGORIES;

@RestController
@RequestMapping(V_1_CATEGORIES)
@RequiredArgsConstructor
public class CategoryController {
    @Autowired
    private final CategoryService service;

    @Operation(summary = "Get a category by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the category",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoryDetailDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Category not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class)) })
    })
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDetailDto>getCategoryById(@PathVariable Long id){
        CategoryDetailDto detailCategory = service.getCategoryById(id);
        return ResponseEntity.ok().body(detailCategory);
    }


    @Operation(summary = "Delete a category by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Delete the category",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Category not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class)) })
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable("id")Long id){
        service.deleteCategory(id);
    }


    @Operation(summary = "Add a new category to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create category",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid field",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class)) })
    })
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<Void> createCategory(UriComponentsBuilder uriComponentsBuilder, @Valid @RequestBody CategoryDto dto){
        final long categoryId = service.createCategory(dto);
        UriComponents uriComponents = uriComponentsBuilder.path(V_1_CATEGORIES + "/{id}").buildAndExpand(categoryId);
        return ResponseEntity.created(uriComponents.toUri()).build();
    }


    @Operation(summary = "Update category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Update category",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid field",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class)) }),
            @ApiResponse(responseCode = "404", description = "Invalid id supplied",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetails.class)) })
    })
    @PutMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateCategory (@PathVariable("id") Long id, @Valid @RequestBody CategoryPutDto putDto){
        service.updateCategory(id, putDto);
    }


    @Operation(summary = "Get a category list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieve a list of categories",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoryListDto.class)) })
    })
    @GetMapping
    public ResponseEntity<List<CategoryListDto>>getCategoryList(){

        return ResponseEntity.ok().body(service.getAllCategories());
    }
}
