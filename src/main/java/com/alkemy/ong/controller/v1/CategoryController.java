package com.alkemy.ong.controller.v1;

import com.alkemy.ong.dto.CategoryDetailDto;
import com.alkemy.ong.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.alkemy.ong.controller.ControllerConstants.V_1_CATEGORIES;

@RestController
@RequestMapping(V_1_CATEGORIES)
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService service;

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDetailDto>getCategoryById(@PathVariable Long id){
        CategoryDetailDto detailCategory = service.getCategoryById(id);
        return ResponseEntity.ok().body(detailCategory);
    }
}
