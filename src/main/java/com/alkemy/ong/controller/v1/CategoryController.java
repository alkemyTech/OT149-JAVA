package com.alkemy.ong.controller.v1;

import com.alkemy.ong.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.alkemy.ong.controller.ControllerConstants.V_1_CATEGORIES;

@RestController
@RequestMapping(V_1_CATEGORIES)
@RequiredArgsConstructor
public class CategoryController {
    @Autowired
    private final CategoryService service;

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable("id")Long id){
        service.deleteCategory(id);
    }
}