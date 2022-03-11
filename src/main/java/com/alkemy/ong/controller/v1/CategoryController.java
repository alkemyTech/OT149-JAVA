package com.alkemy.ong.controller.v1;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.dto.CategoryDetailDto;
import com.alkemy.ong.dto.CategoryListDto;
import com.alkemy.ong.dto.CategoryPutDto;
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

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDetailDto>getCategoryById(@PathVariable Long id){
        CategoryDetailDto detailCategory = service.getCategoryById(id);
        return ResponseEntity.ok().body(detailCategory);
    }
        

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable("id")Long id){
        service.deleteCategory(id);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<Void> createCategory(UriComponentsBuilder uriComponentsBuilder, @Valid @RequestBody CategoryDto dto){
        final long categoryId = service.createCategory(dto);
        UriComponents uriComponents = uriComponentsBuilder.path("/{id}").buildAndExpand(categoryId);
        return ResponseEntity.created(uriComponents.toUri()).build();
    }
    
    @PutMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateCategory (@PathVariable("id") Long id, @Valid @RequestBody CategoryPutDto putDto){
        service.updateCategory(id, putDto);
    }

    @GetMapping
    public ResponseEntity<List<CategoryListDto>>getCategoryList(){

        return ResponseEntity.ok().body(service.getAllCategories());
    }
}
