package com.alkemy.ong.controller.v1;

import com.alkemy.ong.H2Config;
import com.alkemy.ong.SecurityConfig;
import com.alkemy.ong.controller.ControllerConstants;
import com.alkemy.ong.dto.NewDetailDto;
import com.alkemy.ong.dto.NewDto;
import com.alkemy.ong.dto.NewPagedList;
import com.alkemy.ong.utils.JsonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.alkemy.ong.utils.TestUtils.matchJson;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
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
class NewControllerIT {

    @Autowired
    MockMvc mockMvc;

    static final int ID = 1;

    @Test
    @Order(1)
    void createNew_shouldRespond201() throws Exception {

        NewDto news = NewDto.builder()
                .name("foo")
                .content("bar")
                .image("bar.png")
                .build();

        final String actualLocation =
                mockMvc.perform(post(ControllerConstants.V_1_NEWS)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JsonUtils.objectToJson(news)))
                        .andExpect(status().isCreated())
                        .andReturn()
                        .getResponse()
                        .getHeader(HttpHeaders.LOCATION);

        Assertions.assertEquals("http://localhost/v1/news/" + ID, actualLocation);
    }

    @Test
    @Order(2)
    void createNew_shouldRespond400() throws Exception {

        NewDto news = NewDto.builder()
                .name("")
                .content("bar")
                .image("bar.png")
                .build();

        mockMvc.perform(post(ControllerConstants.V_1_NEWS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(news)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(3)
    void getNewById_shouldRespond200() throws Exception {

        final String expected = "{\"name\":\"foo\",\"content\":\"bar\",\"image\":\"bar.png\",\"categoryId\":null,\"type\":null}";

        assertTrue(
                matchJson(
                        mockMvc.perform(get(ControllerConstants.V_1_NEWS + "/" + ID))
                                .andExpect(status().isOk())
                                .andReturn()
                                .getResponse()
                                .getContentAsString(),
                        expected
                )
        );

    }

    @Test
    @Order(4)
    void getNewById_shouldRespond404() throws Exception {

        mockMvc.perform(get(ControllerConstants.V_1_NEWS + "/20"))
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(5)
    void saveOrUpdateNews_shouldRespond201() throws Exception {

        NewDetailDto news = NewDetailDto.builder()
                .name("bar")
                .content("foo")
                .image("foo.png")
                .build();

        final String expected = "{\"name\":\"bar\",\"content\":\"foo\",\"image\":\"foo.png\",\"categoryId\":null,\"type\":null}";

        assertTrue(
                matchJson(
                        mockMvc.perform(put(ControllerConstants.V_1_NEWS + "/" + ID)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(JsonUtils.objectToJson(news)))
                                .andExpect(status().isCreated())
                                .andReturn()
                                .getResponse()
                                .getContentAsString(),
                        expected)
        );
    }

    @Test
    @Order(6)
    void saveOrUpdateNews_shouldRespond404() throws Exception {

        NewDetailDto news = NewDetailDto.builder()
                .name("bar")
                .content("foo")
                .image("foo.png")
                .build();

        mockMvc.perform(put(ControllerConstants.V_1_NEWS + "/20")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(news)))
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(7)
    void list_shouldRespond200() throws Exception {

        String expected = "{\"content\":[{\"name\":\"bar\",\"content\":\"foo\",\"image\":\"foo.png\",\"categoryId\":null,\"type\":null}],\"number\":0,\"size\":10,\"totalElements\":1,\"pageable\":{\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"offset\":0,\"pageNumber\":0,\"pageSize\":10,\"paged\":true,\"unpaged\":false},\"last\":true,\"totalPages\":1,\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"first\":true,\"numberOfElements\":1,\"nextUri\":\"http://localhost/v1/news?pageNumber=0\",\"backUri\":\"http://localhost/v1/news?pageNumber=0\",\"empty\":false}";

        assertTrue(
                matchJson(
                        mockMvc.perform(get(ControllerConstants.V_1_NEWS))
                                .andExpect(status().isOk())
                                .andReturn()
                                .getResponse()
                                .getContentAsString(),
                        expected
                )
        );
    }

    @Test
    @Order(8)
    void deleteNew_shouldRespond204() throws Exception {

        mockMvc.perform(delete(ControllerConstants.V_1_NEWS + "/" + ID))
                .andExpect(status().isNoContent());
    }

    @Test
    @Order(9)
    void deleteNew_shouldRespond404() throws Exception {

        mockMvc.perform(delete(ControllerConstants.V_1_NEWS + "/20"))
                .andExpect(status().isNotFound());
    }
}