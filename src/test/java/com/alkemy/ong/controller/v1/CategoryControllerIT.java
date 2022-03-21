package com.alkemy.ong.controller.v1;

import com.alkemy.ong.H2Config;
import com.alkemy.ong.SecurityConfig;
import com.alkemy.ong.controller.ControllerConstants;
import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.utils.JsonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import static com.alkemy.ong.utils.TestUtils.matchJson;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringJUnitConfig(classes = {H2Config.class, SecurityConfig.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategoryControllerIT {
    @Autowired
    MockMvc mockMvc;


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

        final String actual = mockMvc.perform(put(ControllerConstants.V_1_CATEGORIES + "/" + 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(categoryDto)))
                .andExpect(status().isNoContent())
                .andReturn()
                .getResponse().getContentAsString();
        Assertions.assertEquals("", actual);

    }

    @Test
    @Order(4)
    void deleteCategory_shouldRespond204() throws Exception {
        final String actual = mockMvc.perform(delete(ControllerConstants.V_1_CATEGORIES + "/" + 3))
                .andExpect(status().isNoContent())
                .andReturn()
                .getResponse().getContentAsString();
        Assertions.assertEquals("", actual);
    }

    @Test
    @Order(5)
    void getCategoryList_shouldRespond200() throws Exception {
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
    @Order(9)
    void updateCategory_shouldRespond404() throws Exception {
        final CategoryDto categoryDto = CategoryDto.builder()
                .name("Categoria Z modificada")
                .description("Esta es la categoria Z modificada")
                .image("https://cohorte-febrero-b35bfd02.s3.amazonaws.com/1646237572762-categoria_Z_modificada.png")
                .build();
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
    @Order(10)
    void deleteCategory_shouldRespond404() throws Exception {
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