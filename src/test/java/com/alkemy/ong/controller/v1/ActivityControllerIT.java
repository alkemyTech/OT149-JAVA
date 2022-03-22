package com.alkemy.ong.controller.v1;

import com.alkemy.ong.H2Config;
import com.alkemy.ong.SecurityConfig;
import com.alkemy.ong.controller.ControllerConstants;
import com.alkemy.ong.dto.ActivityDto;
import com.alkemy.ong.exception.ActivityNotFoundException;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringJUnitConfig(classes = {H2Config.class, SecurityConfig.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ActivityControllerIT {

    @Autowired
    MockMvc mockMvc;

    static final int ID = 1;

    @Test
    @Order(1)
    void addActivity_shouldRespond201() throws Exception {

        ActivityDto activity = ActivityDto.builder()
                .name("Activity 1")
                .text("My Activity 1")
                .image("Activity_1.jpg")
                .build();


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
    @Order(2)
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
    @Order(3)
    void updateActivity_shouldRespond202() throws Exception {

        ActivityDto activity = ActivityDto.builder()
                .name("Activity 1")
                .text("My Activity 1")
                .image("Activity_1.jpg")
                .build();


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
    @Order(4)
    void updateActivity_shouldRespond400() throws Exception {

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
    @Order(5)
    void updateActivity_shouldRespond404() throws Exception {

        final long ID = 4;


        ActivityDto activity = ActivityDto.builder()
                .name("Activity 1")
                .text("My Activity 1")
                .image("Activity_1.jpg")
                .build();


        mockMvc.perform(put(ControllerConstants.REQ_MAPP_ACTIVITIES + "/" + ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(activity)))
                .andExpect(status().isNotFound());

    }

}