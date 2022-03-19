package com.alkemy.ong.controller.v1;

import com.alkemy.ong.controller.ControllerConstants;
import com.alkemy.ong.dto.ActivityDto;
import com.alkemy.ong.exception.ActivityNotFoundException;
import com.alkemy.ong.exception.GlobalControllerExceptionHandler;
import com.alkemy.ong.service.ActivityService;
import com.alkemy.ong.service.impl.ActivityServiceImp;
import com.alkemy.ong.utils.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.alkemy.ong.utils.TestUtils.matchJson;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class ActivityControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ActivityController controller;

    @Mock
    private ActivityServiceImp service;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(GlobalControllerExceptionHandler.class)
                .build();
    }

    @Test
    void addActivity_shouldRespond201() throws Exception {
        final long ID = 1;

        ActivityDto activity = ActivityDto.builder()
                .name("Activity 1")
                .text("My Activity 1")
                .image("Activity_1.jpg")
                .build();

        when(service.saveActivity(eq(activity))).thenReturn(ID);

        final String actualLocation = mockMvc.perform(post(ControllerConstants.REQ_MAPP_ACTIVITIES)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtils.objectToJson(activity)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getHeader(HttpHeaders.LOCATION);

        Assertions.assertEquals("http://localhost/v1/activities/1", actualLocation);
    }

    @Test
    void addActivity_shouldRespond400() throws Exception {

        ActivityDto activity = ActivityDto.builder()
                .name("")
                .text("")
                .image("")
                .build();

        mockMvc.perform(post(ControllerConstants.REQ_MAPP_ACTIVITIES)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(activity)))
                .andExpect(status().isBadRequest());
    }


    @Test
    void updateActivity_shouldRespond202() throws Exception {

        final long ID = 1;

        ActivityDto activity = ActivityDto.builder()
                .name("Activity 1")
                .text("My Activity 1")
                .image("Activity_1.jpg")
                .build();

when(service.updateActivity(eq(ID), eq(activity))).thenReturn(activity);

        final String expected = "{\"name\":\"Activity 1\",\"text\":\"My Activity 1\",\"image\":\"Activity_1.jpg\"}";

        assertTrue(
                matchJson(
                        mockMvc.perform(put(ControllerConstants.REQ_MAPP_ACTIVITIES + "/" + ID)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(JsonUtils.objectToJson(activity)))
                                .andExpect(status().isAccepted())
                                .andReturn()
                                .getResponse()
                                .getContentAsString(), expected
                )

        );



    }



    @Test
    void updateActivity_shouldRespond400() throws Exception {

        final long ID = 1;

        ActivityDto activity = ActivityDto.builder()
                .name("")
                .text("")
                .image("")
                .build();

        mockMvc.perform(put(ControllerConstants.REQ_MAPP_ACTIVITIES + "/" + ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(activity)))
                .andExpect(status().isBadRequest());

    }

    @Test
    void updateActivity_shouldRespond404() throws Exception {

        final long ID = 4;


        ActivityDto activity = ActivityDto.builder()
                .name("Activity 1")
                .text("My Activity 1")
                .image("Activity_1.jpg")
                .build();


        doThrow(new ActivityNotFoundException()).when(service).updateActivity(ID,activity);


                        mockMvc.perform(put(ControllerConstants.REQ_MAPP_ACTIVITIES + "/" + ID)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(JsonUtils.objectToJson(activity)))
                                .andExpect(status().isNotFound());

    }
}