package com.alkemy.ong.controller.v1;

import com.alkemy.ong.H2Config;
import com.alkemy.ong.SecurityConfig;
import com.alkemy.ong.controller.ControllerConstants;
import com.alkemy.ong.dto.UserPatchDTO;
import com.alkemy.ong.utils.JsonUtils;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;


import static com.alkemy.ong.utils.TestUtils.matchJson;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringJUnitConfig(classes = {H2Config.class, SecurityConfig.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerIT {

    @Autowired
    MockMvc mockMvc;

    @Test
    @Order(1)
    void userPatch_shouldRespond204() throws Exception {

        final UserPatchDTO user = UserPatchDTO.builder().firstName("John").lastName("Foo").photo("foo.jpg").build();

        mockMvc.perform(patch(ControllerConstants.V_1_USERS + "/1").contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.objectToJson(user))).andExpect(status().isNoContent());
    }

    @Test
    @Order(2)
    void userPatch_shouldRespond400() throws Exception {

        final UserPatchDTO user = UserPatchDTO.builder().firstName("").lastName("").photo("").build();

        mockMvc.perform(patch(ControllerConstants.V_1_USERS + "/1").contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.objectToJson(user))).andExpect(status().isBadRequest());
    }

    @Test
    @Order(3)
    void userPatch_shouldRespond404() throws Exception {

        final UserPatchDTO user = UserPatchDTO.builder().firstName("Jane").lastName("Bar").photo("foo.jpg").build();

        mockMvc.perform(patch(ControllerConstants.V_1_USERS + "/2").contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.objectToJson(user))).andExpect(status().isNotFound());
    }

    @Test
    @Order(4)
    void list_shouldRespond200() throws Exception {

        final String expected = "{\"content\":[{\"firstName\":\"John\",\"lastName\":\"Foo\",\"photo\":\"foo.jpg\"}],\"number\":0,\"size\":10,\"totalElements\":1,\"pageable\":{\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"offset\":0,\"pageNumber\":0,\"pageSize\":10,\"paged\":true,\"unpaged\":false},\"last\":true,\"totalPages\":1,\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"first\":true,\"numberOfElements\":1,\"empty\":false}";

        assertTrue(matchJson(mockMvc.perform(get(ControllerConstants.V_1_USERS))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(), expected));
    }

    @Test
    @Order(5)
    void deleteUser_shouldRespond204() throws Exception {

        mockMvc.perform(delete(ControllerConstants.V_1_USERS + "/1")).andExpect(status().isNoContent());
    }
}
