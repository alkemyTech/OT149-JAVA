package com.alkemy.ong.controller.v1;

import com.alkemy.ong.controller.ControllerConstants;
import com.alkemy.ong.dto.NewDetailDto;
import com.alkemy.ong.dto.NewDto;
import com.alkemy.ong.exception.GlobalControllerExceptionHandler;
import com.alkemy.ong.exception.NewNotFoundException;
import com.alkemy.ong.model.New;
import com.alkemy.ong.service.NewService;
import com.alkemy.ong.utils.JsonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static com.alkemy.ong.utils.TestUtils.matchJson;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class NewControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private NewController controller;

    @Mock
    private NewService service;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(GlobalControllerExceptionHandler.class)
                .build();
    }

    @Test
    void createNew_shouldRespond201() throws Exception {

        final long expectedId = 10;

        NewDto news = NewDto.builder()
                .name("foo")
                .content("bar")
                .image("bar.png")
                .build();

        when(service.createNew(eq(news))).thenReturn(expectedId);

        final String actualLocation =
                mockMvc.perform(post(ControllerConstants.V_1_NEWS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(news)))
                        .andExpect(status().isCreated())
                        .andReturn()
                        .getResponse()
                        .getHeader(HttpHeaders.LOCATION);

        Assertions.assertEquals("http://localhost/v1/news/10", actualLocation);
    }

    @Test
    void createNew_shouldRespond400() throws Exception {
        NewDto news = NewDto.builder()
                .name("")
                .content("")
                .image("")
                .build();

        mockMvc.perform(post(ControllerConstants.V_1_NEWS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.objectToJson(news)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getNewById_shouldRespond200() throws Exception {

        final long expectedId = 10;

        NewDetailDto news = NewDetailDto.builder()
                .name("Jhon")
                .content("Foo")
                .image("foo.png")
                .build();

        given(service.getNewById(expectedId)).willReturn(news);

        mockMvc.perform(get(ControllerConstants.V_1_NEWS + "/" + expectedId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.objectToJson(news)))
                .andExpect(status().isOk());

    }

    @Test
    void getNewById_shouldRespond404() throws Exception {

        final long expectedId=10;

        NewDetailDto news = NewDetailDto.builder()
                .name("Jhon")
                .content("Foo")
                .image("foo.png")
                .build();

        doThrow(new NewNotFoundException()).when(service).getNewById(expectedId);

        mockMvc.perform(get(ControllerConstants.V_1_NEWS + "/" + expectedId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.objectToJson(news)))
                .andExpect(status().isNotFound());
    }

    @Test
    void saveOrUpdateNews_shouldRespond201() throws Exception {

        final long expectedId = 10;

        NewDto newToUpdate = NewDto.builder()
                .name("bar")
                .content("foo")
                .image("foo.png")
                .build();

        NewDetailDto newUpdated = NewDetailDto.builder()
                .name("bar")
                .content("foo")
                .image("foo.png")
                .build();

        when(service.addNews(newToUpdate, expectedId)).thenReturn(newUpdated);

        final String expected = "{\"name\":\"bar\",\"content\":\"foo\",\"image\":\"foo.png\",\"categoryId\":null,\"type\":null}";

        assertTrue(
                matchJson(
                        mockMvc.perform(put(ControllerConstants.V_1_NEWS + "/" + expectedId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JsonUtils.objectToJson(newUpdated)))
                                .andExpect(status().isCreated())
                                .andReturn()
                                .getResponse()
                                .getContentAsString(),
                        expected)
                );
    }

    @Test
    void saveOrUpdateNews_shouldRespond404() throws Exception {
        final long expectedId=10;

        NewDto news = NewDto.builder()
                .name("Jhon")
                .content("Foo")
                .image("foo.png")
                .build();

        doThrow(new NewNotFoundException()).when(service).addNews(news, expectedId);

        mockMvc.perform(put(ControllerConstants.V_1_NEWS + "/" + expectedId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(news)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteNew_shouldRespond204() throws Exception {
        final long expectedId = 10;

        doNothing().when(service).deleteNew(eq(expectedId));

        mockMvc.perform(delete(ControllerConstants.V_1_NEWS + "/" + expectedId))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteNew_shouldRespond404() throws Exception {
        final long expectedId = 10;

        doThrow(new NewNotFoundException()).when(service).deleteNew(expectedId);

        mockMvc.perform(delete(ControllerConstants.V_1_NEWS + "/" + expectedId))
                .andExpect(status().isNotFound());


    }
}