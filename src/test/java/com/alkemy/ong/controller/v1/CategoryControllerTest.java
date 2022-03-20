package com.alkemy.ong.controller.v1;

import com.alkemy.ong.controller.ControllerConstants;
import com.alkemy.ong.dto.CategoryDetailDto;
import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.dto.CategoryListDto;
import com.alkemy.ong.dto.CategoryPutDto;
import com.alkemy.ong.exception.CategoryNotFoundException;
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
import static org.mockito.Mockito.doThrow;
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
            final char LETTER = (char) (i + 64);
            final CategoryDto categoryDto = CategoryDto.builder()
                    .name("Categoria " + LETTER)
                    .description("Esta es la categoria " + LETTER)
                    .image("https://cohorte-febrero-b35bfd02.s3.amazonaws.com/1646237572762-categoria_" + LETTER + ".png")
                    .build();

            when(service.createCategory(eq(categoryDto))).thenReturn(i);
            final String actual = mockMvc.perform(post(ControllerConstants.V_1_CATEGORIES)
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
                .name("Categoria A")
                .description("Esta es la categoria A")
                .image("https://cohorte-febrero-b35bfd02.s3.amazonaws.com/1646237572762-categoria_A.png")
                .build();
        when(service.getCategoryById(eq(1L))).thenReturn(categoryDetailDto);
        final String expected = "{\"name\":\"Categoria A\",\"description\":\"Esta es la categoria A\",\"image\":\"https://cohorte-febrero-b35bfd02.s3.amazonaws.com/1646237572762-categoria_A.png\"}";

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
                .name("Categoria A modificada")
                .description("Esta es la categoria A modificada")
                .image("https://cohorte-febrero-b35bfd02.s3.amazonaws.com/1646237572762-categoria_A_modificada.png")
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
                        new CategoryListDto("Categoria A modificada"),
                        new CategoryListDto("Categoria B")
                )
        );

        when(service.getAllCategories()).thenReturn(dtos);
        final String expected = "[{\"name\":\"Categoria A modificada\"},{\"name\":\"Categoria B\"}]";
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

    @Test
    @Order(6)
    void createCategory_shouldRespond400() throws Exception {


        final CategoryDto categoryDto = CategoryDto.builder()
                .name(" ")
                .description("Esta es una categoria sin nombre")
                .image("")
                .build();

        final String expected = "[{\"code\":\"INVALID_FIELD_VALUE\",\"description\":\"Obligatory field.\",\"field\":\"name\",\"location\":\"BODY\"}]";
        assertTrue(
                matchJson(mockMvc.perform(post(ControllerConstants.V_1_CATEGORIES)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(JsonUtils.objectToJson(categoryDto)))
                                .andExpect(status().isBadRequest())
                                .andReturn()
                                .getResponse()
                                .getContentAsString(),
                        expected
                )
        );

    }

    @Test
    @Order(7)
    void getCategoryById_shouldRespond404() throws Exception {

        doThrow(new CategoryNotFoundException("Category not found with id 26")).when(service).getCategoryById(eq(26L));

        final String expected = "{\"code\":\"NOT_FOUND\",\"description\":\"Category not found with id 26\",\"field\":\"id\",\"location\":\"PATH\"}";

        assertTrue(
                matchJson(mockMvc.perform(get(ControllerConstants.V_1_CATEGORIES + "/" + 26))
                                .andExpect(status().isNotFound())
                                .andReturn()
                                .getResponse()
                                .getContentAsString(),
                        expected
                )
        );
    }


    @Test
    @Order(8)
    void updateCategory_shouldRespond400() throws Exception {
        final CategoryPutDto categoryPutDto = CategoryPutDto.builder()
                .name(" ")
                .description("Categoria A modificada pero sin nombre")
                .image("https://cohorte-febrero-b35bfd02.s3.amazonaws.com/1646237572762-categoria_A_modificada.png")
                .build();
        final String expected = "[{\"code\":\"INVALID_FIELD_VALUE\",\"description\":\"Obligatory field.\",\"field\":\"name\",\"location\":\"BODY\"}]";

        assertTrue(
                matchJson(mockMvc.perform(put(ControllerConstants.V_1_CATEGORIES + "/" + 1)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(JsonUtils.objectToJson(categoryPutDto)))
                                .andExpect(status().isBadRequest())
                                .andReturn()
                                .getResponse().getContentAsString(),
                        expected
                )
        );

    }

    @Test
    @Order(9)
    void updateCategory_shouldRespond404() throws Exception {
        final CategoryPutDto categoryPutDto = CategoryPutDto.builder()
                .name("Categoria Z modificada")
                .description("Esta es la categoria Z modificada")
                .image("https://cohorte-febrero-b35bfd02.s3.amazonaws.com/1646237572762-categoria_Z_modificada.png")
                .build();
        doThrow(new CategoryNotFoundException("Category not found with id 26")).when(service).updateCategory(eq(26L), eq(categoryPutDto));

        final String expected = "{\"code\":\"NOT_FOUND\",\"description\":\"Category not found with id 26\",\"field\":\"id\",\"location\":\"PATH\"}";

        assertTrue(
                matchJson(mockMvc.perform(put(ControllerConstants.V_1_CATEGORIES + "/" + 26)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(JsonUtils.objectToJson(categoryPutDto)))
                                .andExpect(status().isNotFound())
                                .andReturn()
                                .getResponse().getContentAsString(),
                        expected
                )
        );
    }

    @Test
    @Order(10)
    void deleteCategory_shouldRespond404() throws Exception {
        doThrow(new CategoryNotFoundException("Category not found with id 26")).when(service).deleteCategory(eq(26L));

        final String expected = "{\"code\":\"NOT_FOUND\",\"description\":\"Category not found with id 26\",\"field\":\"id\",\"location\":\"PATH\"}";

        assertTrue(
                matchJson(mockMvc.perform(delete(ControllerConstants.V_1_CATEGORIES + "/" + 26))
                                .andExpect(status().isNotFound())
                                .andReturn()
                                .getResponse().getContentAsString(),
                        expected
                )
        );
    }


}