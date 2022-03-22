package com.alkemy.ong.controller.v1;

import com.alkemy.ong.controller.ControllerConstants;
import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.dto.MemberPagedList;
import com.alkemy.ong.exception.GlobalControllerExceptionHandler;
import com.alkemy.ong.exception.MemberNotFoundException;
import com.alkemy.ong.service.MemberService;
import com.alkemy.ong.utils.JsonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.alkemy.ong.utils.TestUtils.matchJson;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class MemberControllerTest {

    static final int ID = 10;
    static final int expectedId = 10;
    private MockMvc mockMvc;
    @InjectMocks
    private MemberController controller;
    @Mock
    private MemberService service;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(GlobalControllerExceptionHandler.class)
                .build();
    }

    @Test
    void list_shouldRespond200() throws Exception {

        final MemberDto member1 = MemberDto.builder()
                .name("member 1")
                .facebookUrl("facebook.com/member")
                .instagramUrl("instagram.com/member")
                .linkedinUrl("linkedin.com/member")
                .image("member.png")
                .description("member test")
                .build();

        final MemberDto member2 = MemberDto.builder()
                .name("member 2")
                .facebookUrl("facebook.com/member")
                .instagramUrl("instagram.com/member")
                .linkedinUrl("linkedin.com/member")
                .image("member.png")
                .description("member test")
                .build();

        MemberPagedList memberPagedList = new MemberPagedList(List.of(member1, member2), PageRequest.of(2, 2), 2);

        when(service.pagedList(any(PageRequest.class))).thenReturn(memberPagedList);

        final String expected = "{\"content\":[{\"name\":\"member 1\",\"facebookUrl\":\"facebook.com/member\",\"instagramUrl\":\"instagram.com/member\",\"linkedinUrl\":\"linkedin.com/member\",\"image\":\"member.png\",\"description\":\"member test\"},{\"name\":\"member 2\",\"facebookUrl\":\"facebook.com/member\",\"instagramUrl\":\"instagram.com/member\",\"linkedinUrl\":\"linkedin.com/member\",\"image\":\"member.png\",\"description\":\"member test\"}],\"number\":2,\"size\":2,\"totalElements\":6,\"pageable\":{\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"offset\":4,\"pageNumber\":2,\"pageSize\":2,\"unpaged\":false,\"paged\":true},\"last\":true,\"totalPages\":3,\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"first\":false,\"numberOfElements\":2,\"nextUri\":\"http://localhost/v1/members?page=2\",\"backUri\":\"http://localhost/v1/members?page=1\",\"empty\":false}";

        assertTrue(matchJson(mockMvc.perform(get(ControllerConstants.V_1_MEMBERS).queryParam("page", "2")
                        .queryParam("pageSize", "2")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(),
                expected));
    }

    @Test
    void list_shouldRespond200_withWrongQueryParam() throws Exception {

        final MemberDto member1 = MemberDto.builder()
                .name("member 1")
                .facebookUrl("facebook.com/member")
                .instagramUrl("instagram.com/member")
                .linkedinUrl("linkedin.com/member")
                .image("member.png")
                .description("member test")
                .build();

        final MemberDto member2 = MemberDto.builder()
                .name("member 2")
                .facebookUrl("facebook.com/member")
                .instagramUrl("instagram.com/member")
                .linkedinUrl("linkedin.com/member")
                .image("member.png")
                .description("member test")
                .build();

        final PageRequest pageRequest = PageRequest.of(ControllerConstants.DEFAULT_PAGE_NUMBER,
                ControllerConstants.DEFAULT_PAGE_SIZE);

        MemberPagedList memberPagedList = new MemberPagedList(List.of(member1, member2), pageRequest, 2);

        when(service.pagedList(eq(pageRequest))).thenReturn(memberPagedList);

        final String expected = "{\"content\":[{\"name\":\"member 1\",\"facebookUrl\":\"facebook.com/member\",\"instagramUrl\":\"instagram.com/member\",\"linkedinUrl\":\"linkedin.com/member\",\"image\":\"member.png\",\"description\":\"member test\"},{\"name\":\"member 2\",\"facebookUrl\":\"facebook.com/member\",\"instagramUrl\":\"instagram.com/member\",\"linkedinUrl\":\"linkedin.com/member\",\"image\":\"member.png\",\"description\":\"member test\"}],\"number\":0,\"size\":10,\"totalElements\":2,\"pageable\":{\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"offset\":0,\"pageNumber\":0,\"pageSize\":10,\"unpaged\":false,\"paged\":true},\"last\":true,\"totalPages\":1,\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"first\":true,\"numberOfElements\":2,\"nextUri\":\"http://localhost/v1/members?page=0\",\"backUri\":\"http://localhost/v1/members?page=0\",\"empty\":false}";

        assertTrue(matchJson(mockMvc.perform(get(ControllerConstants.V_1_MEMBERS).queryParam("page", "-1")
                        .queryParam("pageSize", "-1"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(), expected));
    }

    @Test
    void updateMember_shouldRespond204() throws Exception {

        final MemberDto member = MemberDto.builder()
                .name("member")
                .facebookUrl("facebook.com/member")
                .instagramUrl("instagram.com/member")
                .linkedinUrl("linkedin.com/member")
                .image("member.png")
                .description("member test")
                .build();

        doNothing().when(service).updateMember(eq(ID), eq(member));

        mockMvc.perform(put(ControllerConstants.V_1_MEMBERS + "/" + ID).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.objectToJson(member))).andExpect(status().isNoContent());
    }

    @Test
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
    void updateMember_shouldRespond404() throws Exception {

        final MemberDto member = MemberDto.builder()
                .name("member")
                .facebookUrl("facebook.com/member")
                .instagramUrl("instagram.com/member")
                .linkedinUrl("linkedin.com/member")
                .image("member.png")
                .description("member test")
                .build();

        doThrow(new MemberNotFoundException()).when(service).updateMember(ID, member);

        mockMvc.perform(put(ControllerConstants.V_1_MEMBERS + "/" + ID).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.objectToJson(member))).andExpect(status().isNotFound());
    }

    @Test
    void deleteMember_shouldRespond204() throws Exception {

        doNothing().when(service).deleteMember(eq(ID));

        mockMvc.perform(delete(ControllerConstants.V_1_MEMBERS + "/" + ID)).andExpect(status().isNoContent());

    }

    @Test
    void addMember_shouldRespond201() throws Exception {

        final MemberDto member = MemberDto.builder()
                .name("member")
                .facebookUrl("facebook.com/member")
                .instagramUrl("instagram.com/member")
                .linkedinUrl("linkedin.com/member")
                .image("member.png")
                .description("member test")
                .build();

        when(service.saveMember(eq(member))).thenReturn(expectedId);

        final String actualLocation = mockMvc.perform(post(ControllerConstants.V_1_MEMBERS).contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(member)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getHeader(HttpHeaders.LOCATION);

        Assertions.assertEquals("http://localhost/v1/members/" + expectedId, actualLocation);
    }

    @Test
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
}