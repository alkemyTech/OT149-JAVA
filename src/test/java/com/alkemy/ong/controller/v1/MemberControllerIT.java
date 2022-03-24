package com.alkemy.ong.controller.v1;

import com.alkemy.ong.H2Config;
import com.alkemy.ong.SecurityConfig;
import com.alkemy.ong.controller.ControllerConstants;
import com.alkemy.ong.dto.MemberDto;
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
class MemberControllerIT {

    @Autowired
    MockMvc mockMvc;

    static final int ID = 1;

    static final int notFoundId = 20;

    @Test
    @Order(1)
    void addMember_shouldRespond201() throws Exception {

        final MemberDto member = MemberDto.builder()
                .name("member")
                .facebookUrl("facebook.com/member")
                .instagramUrl("instagram.com/member")
                .linkedinUrl("linkedin.com/member")
                .image("member.png")
                .description("member test")
                .build();

        final String actualLocation = mockMvc.perform(post(ControllerConstants.V_1_MEMBERS).contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(member)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getHeader(HttpHeaders.LOCATION);

        Assertions.assertEquals("http://localhost/v1/members/" + ID, actualLocation);
    }

    @Test
    @Order(2)
    void addMember_shouldRespond400() throws Exception {

        final MemberDto member = MemberDto.builder()
                .name("")
                .facebookUrl("")
                .instagramUrl("")
                .linkedinUrl("")
                .image("")
                .description("")
                .build();

        mockMvc.perform(post(ControllerConstants.V_1_MEMBERS).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.objectToJson(member))).andExpect(status().isBadRequest());
    }

    @Test
    @Order(3)
    void updateMember_shouldRespond204() throws Exception {

        final MemberDto member = MemberDto.builder()
                .name("member update")
                .facebookUrl("facebook.com/member")
                .instagramUrl("instagram.com/member")
                .linkedinUrl("linkedin.com/member")
                .image("member.png")
                .description("member test")
                .build();

        mockMvc.perform(put(ControllerConstants.V_1_MEMBERS + "/" + ID).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.objectToJson(member))).andExpect(status().isNoContent());
    }

    @Test
    @Order(4)
    void updateMember_shouldRespond400() throws Exception {

        final MemberDto member = MemberDto.builder()
                .name("")
                .facebookUrl("")
                .instagramUrl("")
                .linkedinUrl("")
                .image("")
                .description("")
                .build();

        mockMvc.perform(put(ControllerConstants.V_1_MEMBERS + "/" + ID).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.objectToJson(member))).andExpect(status().isBadRequest());
    }

    @Test
    @Order(5)
    void updateMember_shouldRespond404() throws Exception {

        final MemberDto member = MemberDto.builder()
                .name("member")
                .facebookUrl("facebook.com/member")
                .instagramUrl("instagram.com/member")
                .linkedinUrl("linkedin.com/member")
                .image("member.png")
                .description("member test")
                .build();

        mockMvc.perform(put(ControllerConstants.V_1_MEMBERS + "/" + notFoundId).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.objectToJson(member))).andExpect(status().isNotFound());
    }

    @Test
    @Order(6)
    void list_shouldRespond200() throws Exception {

        final String expected = "{\"content\":[{\"name\":\"member update\",\"facebookUrl\":\"facebook.com/member\",\"instagramUrl\":\"instagram.com/member\",\"linkedinUrl\":\"linkedin.com/member\",\"image\":\"member.png\",\"description\":\"member test\"}],\"number\":0,\"size\":10,\"totalElements\":1,\"pageable\":{\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"offset\":0,\"pageNumber\":0,\"pageSize\":10,\"unpaged\":false,\"paged\":true},\"last\":true,\"totalPages\":1,\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"first\":true,\"numberOfElements\":1,\"nextUri\":\"http://localhost/v1/members?page=0\",\"backUri\":\"http://localhost/v1/members?page=0\",\"empty\":false}";

        assertTrue(matchJson(mockMvc.perform(get(ControllerConstants.V_1_MEMBERS))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(), expected));
    }

    @Test
    @Order(7)
    void deleteMember_shouldRespond204() throws Exception {
        mockMvc.perform(delete(ControllerConstants.V_1_MEMBERS + "/" + ID)).andExpect(status().isNoContent());
    }

}