package com.alkemy.ong.controller.v1;

import com.alkemy.ong.H2Config;
import com.alkemy.ong.SecurityConfig;
import com.alkemy.ong.controller.ControllerConstants;
import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.dto.CategoryPutDto;
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

            final CategoryDto categoryDto = CategoryDto.builder()
                    .name("Categoria " + i)
                    .description("Esta es la categoria " + i)
                    .image("https://cohorte-febrero-b35bfd02.s3.amazonaws.com/1646237572762-categoria_" + i + ".png")
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

        final String actual = mockMvc.perform(put(ControllerConstants.V_1_CATEGORIES + "/" + 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(categoryPutDto)))
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