package com.alkemy.ong.controller.v1;

import com.alkemy.ong.H2Config;
import com.alkemy.ong.SecurityConfig;
import com.alkemy.ong.controller.ControllerConstants;
import com.alkemy.ong.dto.UserPatchDTO;
import com.alkemy.ong.utils.JsonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringJUnitConfig(classes = {H2Config.class, SecurityConfig.class})
class UserControllerIT {

    @Autowired
    MockMvc mockMvc;

    @Test
    void userPatch_shouldRespond204() throws Exception {

        final UserPatchDTO user = UserPatchDTO.builder()
                .firstName("John")
                .lastName("Foo")
                .photo("foo.jpg")
                .build();

        mockMvc.perform(patch(ControllerConstants.V_1_USERS + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(user)))
                .andExpect(status().isNoContent());
    }

    @Test
    void userPatch_shouldRespond404() throws Exception {

        final UserPatchDTO user = UserPatchDTO.builder()
                .firstName("Jane")
                .lastName("Bar")
                .photo("foo.jpg")
                .build();

        mockMvc.perform(patch(ControllerConstants.V_1_USERS + "/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(user)))
                .andExpect(status().isNotFound());
    }
}
