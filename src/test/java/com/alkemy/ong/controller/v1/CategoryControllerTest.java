package com.alkemy.ong.controller.v1;

import com.alkemy.ong.controller.ControllerConstants;
import com.alkemy.ong.dto.CategoryDetailDto;
import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.dto.CategoryListDto;
import com.alkemy.ong.dto.CategoryPagedList;
import com.alkemy.ong.exception.CategoryNotFoundException;
import com.alkemy.ong.exception.GlobalControllerExceptionHandler;
import com.alkemy.ong.service.CategoryService;
import com.alkemy.ong.utils.JsonUtils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

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

    @Order(1)
    @DisplayName("Create Category")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @MethodSource("provideLettersForCreateCategory")
    void createCategory_shouldRespond201(String letter, Long idExpected) throws Exception {
        final CategoryDto categoryDto = CategoryDto.builder()
                .name("Categoria " + letter)
                .description("Esta es la categoria " + letter)
                .image("https://cohorte-febrero-b35bfd02.s3.amazonaws.com/1646237572762-categoria_" + letter + ".png")
                .build();
        when(service.createCategory(eq(categoryDto))).thenReturn(idExpected);
        final String actual = mockMvc.perform(post(ControllerConstants.V_1_CATEGORIES)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(categoryDto)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getHeader(HttpHeaders.LOCATION);
        Assertions.assertEquals("http://localhost/v1/categories/" + idExpected, actual);

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
        final CategoryDto categoryDto = CategoryDto.builder()
                .name("Categoria A modificada")
                .description("Esta es la categoria A modificada")
                .image("https://cohorte-febrero-b35bfd02.s3.amazonaws.com/1646237572762-categoria_A_modificada.png")
                .build();
        doNothing().when(service).updateCategory(eq(1L), eq(categoryDto));
        mockMvc.perform(put(ControllerConstants.V_1_CATEGORIES + "/" + 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(categoryDto)))
                .andExpect(status().isNoContent());


    }

    @Test
    @Order(4)
    void deleteCategory_shouldRespond204() throws Exception {
        doNothing().when(service).deleteCategory(eq(6L));
        mockMvc.perform(delete(ControllerConstants.V_1_CATEGORIES + "/" + 6))
                .andExpect(status().isNoContent());

    }

    @Test
    @Order(5)
    void getCategoryList_shouldRespond200() throws Exception {
        final List<CategoryListDto> list = new ArrayList<>(
                Arrays.asList(

                        new CategoryListDto("Categoria C"),
                        new CategoryListDto("Categoria D")

                )
        );
        final PageRequest pageRequest = PageRequest.of(1, 2);
        final CategoryPagedList categoryPagedList = new CategoryPagedList(list, pageRequest, 5);

        when(service.getAllCategories(eq(pageRequest))).thenReturn(categoryPagedList);
        final String expected = "{\"content\":[{\"name\":\"Categoria C\"},{\"name\":\"Categoria D\"}],\"number\":1,\"size\":2,\"totalElements\":5,\"pageable\":{\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"offset\":2,\"pageNumber\":1,\"pageSize\":2,\"unpaged\":false,\"paged\":true},\"last\":false,\"totalPages\":3,\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"first\":false,\"numberOfElements\":2,\"nextUri\":\"http://localhost/v1/categories?pageNumber=2\",\"backUri\":\"http://localhost/v1/categories?pageNumber=0\",\"empty\":false}";
        assertTrue(
                matchJson(mockMvc.perform(get(ControllerConstants.V_1_CATEGORIES).queryParam("page", "1").queryParam("pageSize", "2"))
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
    void getCategoryList_shouldRespond200_withWrongQueryParam() throws Exception {
        final List<CategoryListDto> list = new ArrayList<>(
                Arrays.asList(
                        new CategoryListDto("Categoria A modificada"),
                        new CategoryListDto("Categoria B"),
                        new CategoryListDto("Categoria C"),
                        new CategoryListDto("Categoria D"),
                        new CategoryListDto("Categoria E")

                )
        );
        final PageRequest pageRequest = PageRequest.of(ControllerConstants.DEFAULT_PAGE_NUMBER, ControllerConstants.DEFAULT_PAGE_SIZE);
        final CategoryPagedList categoryPagedList = new CategoryPagedList(list, pageRequest, 5);

        when(service.getAllCategories(eq(pageRequest))).thenReturn(categoryPagedList);
        final String expected = "{\"content\":[{\"name\":\"Categoria A modificada\"},{\"name\":\"Categoria B\"},{\"name\":\"Categoria C\"},{\"name\":\"Categoria D\"},{\"name\":\"Categoria E\"}],\"number\":0,\"size\":10,\"totalElements\":5,\"pageable\":{\"sort\":{\"unsorted\":true,\"sorted\":false,\"empty\":true},\"offset\":0,\"pageSize\":10,\"pageNumber\":0,\"paged\":true,\"unpaged\":false},\"last\":true,\"totalPages\":1,\"sort\":{\"unsorted\":true,\"sorted\":false,\"empty\":true},\"first\":true,\"numberOfElements\":5,\"nextUri\":\"http://localhost/v1/categories?pageNumber=0\",\"backUri\":\"http://localhost/v1/categories?pageNumber=0\",\"empty\":false}";

        assertTrue(
                matchJson(mockMvc.perform(get(ControllerConstants.V_1_CATEGORIES).queryParam("page", "-1").queryParam("pageSize", "-1"))
                                .andExpect(status().isOk())
                                .andReturn()
                                .getResponse()
                                .getContentAsString(),
                        expected
                )
        );

    }

    @Test
    @Order(7)
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
    @Order(8)
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
    @Order(9)
    void updateCategory_shouldRespond400() throws Exception {
        final CategoryDto categoryDto = CategoryDto.builder()
                .name(" ")
                .description("Categoria A modificada pero sin nombre")
                .image("https://cohorte-febrero-b35bfd02.s3.amazonaws.com/1646237572762-categoria_A_modificada.png")
                .build();
        final String expected = "[{\"code\":\"INVALID_FIELD_VALUE\",\"description\":\"Obligatory field.\",\"field\":\"name\",\"location\":\"BODY\"}]";

        assertTrue(
                matchJson(mockMvc.perform(put(ControllerConstants.V_1_CATEGORIES + "/" + 1)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(JsonUtils.objectToJson(categoryDto)))
                                .andExpect(status().isBadRequest())
                                .andReturn()
                                .getResponse().getContentAsString(),
                        expected
                )
        );

    }

    @Test
    @Order(10)
    void updateCategory_shouldRespond404() throws Exception {
        final CategoryDto categoryDto = CategoryDto.builder()
                .name("Categoria Z modificada")
                .description("Esta es la categoria Z modificada")
                .image("https://cohorte-febrero-b35bfd02.s3.amazonaws.com/1646237572762-categoria_Z_modificada.png")
                .build();
        doThrow(new CategoryNotFoundException("Category not found with id 26")).when(service).updateCategory(eq(26L), eq(categoryDto));

        final String expected = "{\"code\":\"NOT_FOUND\",\"description\":\"Category not found with id 26\",\"field\":\"id\",\"location\":\"PATH\"}";

        assertTrue(
                matchJson(mockMvc.perform(put(ControllerConstants.V_1_CATEGORIES + "/" + 26)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(JsonUtils.objectToJson(categoryDto)))
                                .andExpect(status().isNotFound())
                                .andReturn()
                                .getResponse().getContentAsString(),
                        expected
                )
        );
    }

    @Test
    @Order(11)
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

    private static Stream<Arguments> provideLettersForCreateCategory() {
        return Stream.of(
                Arguments.of("A", 1L),
                Arguments.of("B", 2L),
                Arguments.of("C", 3L),
                Arguments.of("D", 4L),
                Arguments.of("E", 5L),
                Arguments.of("F", 6L)
        );
    }

}