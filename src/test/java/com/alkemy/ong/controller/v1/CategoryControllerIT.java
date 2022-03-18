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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringJUnitConfig(classes = {H2Config.class, SecurityConfig.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategoryControllerIT {
    @Autowired
    MockMvc mockMvc;

    static final int[] ARRAY_ID = {1, 2, 3};

    @Test
    @Order(1)
    void createCategory_shouldRespond201() throws Exception {

        CategoryDto categoryDto1 = CategoryDto.builder()
                .name("Novedades")
                .description("Las ultimas novedades de la ONG")
                .image("https://cohorte-febrero-b35bfd02.s3.amazonaws.com/1646237572762-LOGO-SOMOS_MAS.png")
                .build();
        CategoryDto categoryDto2 = CategoryDto.builder()
                .name("Eventos")
                .description("Los ultimos eventos de la ONG")
                .image("https://cohorte-febrero-b35bfd02.s3.amazonaws.com/1646237572762-LOGO-SOMOS_MAS.png")
                .build();

        CategoryDto categoryDto3 = CategoryDto.builder()
                .name("Donaciones")
                .description("Las ultimas donaciones de la ONG")
                .image("https://cohorte-febrero-b35bfd02.s3.amazonaws.com/1646237572762-LOGO-SOMOS_MAS.png")
                .build();
        final CategoryDto[] dtos = {categoryDto1, categoryDto2, categoryDto3};
        for (int i = 0; i < 3; i++) {
            final String id = mockMvc.perform(post(ControllerConstants.V_1_CATEGORIES)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(JsonUtils.objectToJson(dtos[i])))
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andReturn()
                    .getResponse()
                    .getHeader(HttpHeaders.LOCATION);
            Assertions.assertEquals("http://localhost/v1/categories/" + ARRAY_ID[i], id);
        }
    }

    @Test
    @Order(2)
    void getCategoryById_shouldRespond200() throws Exception {
        final String expectedCategoryDetailDto = "{\"name\":\"Novedades\",\"description\":\"Las ultimas novedades de la ONG\",\"image\":\"https://cohorte-febrero-b35bfd02.s3.amazonaws.com/1646237572762-LOGO-SOMOS_MAS.png\"}";
        final String result = mockMvc.perform(get(ControllerConstants.V_1_CATEGORIES + "/" + ARRAY_ID[0]))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        Assertions.assertEquals(result, expectedCategoryDetailDto);
    }

    @Test
    @Order(3)
    void updateCategory_shouldRespond204() throws Exception {
        CategoryPutDto categoryPutDto = CategoryPutDto.builder()
                .name("Sorteos")
                .description("Proximos sorteos de la ONG")
                .image("https://cohorte-febrero-b35bfd02.s3.amazonaws.com/1646246414725-Manos_10.jpg")
                .build();
        final String result = mockMvc.perform(put(ControllerConstants.V_1_CATEGORIES + "/" + ARRAY_ID[1])
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(categoryPutDto)))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn()
                .getResponse().getContentAsString();
        Assertions.assertEquals(result, "");

    }

    @Test
    @Order(4)
    void deleteCategory_shouldRespond204() throws Exception {
        final String result = mockMvc.perform(delete(ControllerConstants.V_1_CATEGORIES + "/" + ARRAY_ID[0]))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn()
                .getResponse().getContentAsString();
        Assertions.assertEquals(result, "");
    }

    @Test
    @Order(5)
    void getCategoryList_shouldRespond200() throws Exception {
        final String expectedCategoryListDto = "[{\"name\":\"Sorteos\"},{\"name\":\"Donaciones\"}]";
        final String result = mockMvc.perform(get(ControllerConstants.V_1_CATEGORIES))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        Assertions.assertEquals(result, expectedCategoryListDto);

    }
}