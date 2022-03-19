package com.alkemy.ong.controller.v1;

import com.alkemy.ong.controller.ControllerConstants;
import com.alkemy.ong.dto.CategoryDetailDto;
import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.dto.CategoryListDto;
import com.alkemy.ong.dto.CategoryPutDto;
import com.alkemy.ong.exception.GlobalControllerExceptionHandler;
import com.alkemy.ong.service.CategoryService;
import com.alkemy.ong.utils.JsonUtils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.alkemy.ong.utils.TestUtils.matchJson;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategoryControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private CategoryController controller;

    @Mock
    private CategoryService service;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(GlobalControllerExceptionHandler.class)
                .build();
    }

    @Test
    @Order(1)
    void createCategory_shouldRespond201() throws Exception {

        for (long i = 1; i < 4; i++) {

            final CategoryDto categoryDto = CategoryDto.builder()
                    .name("Categoria " + i)
                    .description("Esta es la categoria " + i)
                    .image("https://cohorte-febrero-b35bfd02.s3.amazonaws.com/1646237572762-categoria_" + i + ".png")
                    .build();

            when(service.createCategory(eq(categoryDto))).thenReturn(i);
            final String actual=mockMvc.perform(post(ControllerConstants.V_1_CATEGORIES)
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content(JsonUtils.objectToJson(categoryDto)))
                                    .andExpect(status().isCreated())
                                    .andReturn()
                                    .getResponse()
                                    .getHeader(HttpHeaders.LOCATION);
            Assertions.assertEquals("http://localhost/v1/categories/" + i, actual);
        }
    }

    @Test
    @Order(2)
    void getCategoryById_shouldRespond200() throws Exception {

        final CategoryDetailDto categoryDetailDto = CategoryDetailDto.builder()
                .name("Categoria 1")
                .description("Esta es la categoria 1")
                .image("https://cohorte-febrero-b35bfd02.s3.amazonaws.com/1646237572762-categoria_1.png")
                .build();
        when(service.getCategoryById(eq(1L))).thenReturn(categoryDetailDto);
        final String expected = "{\"name\":\"Categoria 1\",\"description\":\"Esta es la categoria 1\",\"image\":\"https://cohorte-febrero-b35bfd02.s3.amazonaws.com/1646237572762-categoria_1.png\"}";

        assertTrue(
                matchJson(mockMvc.perform(get(ControllerConstants.V_1_CATEGORIES + "/" + 1))
                                .andExpect(status().isOk())
                                .andReturn()
                                .getResponse()
                                .getContentAsString(),
                        expected
                )
        );
    }

    @Test
    @Order(3)
    void updateCategory_shouldRespond204() throws Exception {
        final CategoryPutDto categoryPutDto = CategoryPutDto.builder()
                .name("Categoria 1 modificada")
                .description("Esta es la categoria 1 modificada")
                .image("https://cohorte-febrero-b35bfd02.s3.amazonaws.com/1646237572762-categoria_1_modificada.png")
                .build();
        doNothing().when(service).updateCategory(eq(1L), eq(categoryPutDto));
        mockMvc.perform(put(ControllerConstants.V_1_CATEGORIES + "/" + 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(categoryPutDto)))
                .andExpect(status().isNoContent());


    }

    @Test
    @Order(4)
    void deleteCategory_shouldRespond204() throws Exception {
        doNothing().when(service).deleteCategory(eq(3L));
        mockMvc.perform(delete(ControllerConstants.V_1_CATEGORIES + "/" + 3))
                .andExpect(status().isNoContent());

    }

    @Test
    @Order(5)
    void getCategoryList_shouldRespond200() throws Exception {
        final List<CategoryListDto> dtos = new ArrayList<>(
                Arrays.asList(
                        new CategoryListDto("Categoria 1 modificada"),
                        new CategoryListDto("Categoria 2")
                )
        );

        when(service.getAllCategories()).thenReturn(dtos);
        final String expected = "[{\"name\":\"Categoria 1 modificada\"},{\"name\":\"Categoria 2\"}]";
        assertTrue(
                matchJson(mockMvc.perform(get(ControllerConstants.V_1_CATEGORIES))
                                .andExpect(status().isOk())
                                .andReturn()
                                .getResponse()
                                .getContentAsString(),
                        expected
                )
        );

    }
}