package com.alkemy.ong.controller.v1;

import com.alkemy.ong.controller.ControllerConstants;
import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.dto.UserPagedList;
import com.alkemy.ong.dto.UserPatchDTO;
import com.alkemy.ong.exception.GlobalControllerExceptionHandler;
import com.alkemy.ong.service.UserService;
import com.alkemy.ong.utils.JsonUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.alkemy.ong.utils.TestUtils.matchJson;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private UserController controller;

    @Mock
    private UserService service;

    static final long ID = 123;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(GlobalControllerExceptionHandler.class)
                .build();
    }

    @Test
    void userPatch_shouldRespond204() throws Exception {

        final UserPatchDTO user = UserPatchDTO.builder()
                .firstName("John")
                .lastName("Foo")
                .photo("foo.jpg")
                .build();

        doNothing().when(service).userPatch(eq(ID), eq(user));

        mockMvc.perform(patch(ControllerConstants.V_1_USERS + "/" + ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(user)))
                .andExpect(status().isNoContent());
    }

    @Test
    void userPatch_shouldRespond400() throws Exception {

        final UserPatchDTO user = UserPatchDTO.builder()
                .firstName("")
                .lastName("")
                .photo("")
                .build();

        mockMvc.perform(patch(ControllerConstants.V_1_USERS + "/" + ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(user)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void list_shouldRespond200() throws Exception {

        final UserPatchDTO user1 = UserPatchDTO.builder()
                .firstName("John")
                .lastName("Foo")
                .photo("foo.jpg")
                .build();

        final UserPatchDTO user2 = UserPatchDTO.builder()
                .firstName("Jane")
                .lastName("Bar")
                .photo("bar.jpg")
                .build();

        UserPagedList userPagedList = new UserPagedList(List.of(user1, user2), PageRequest.of(0, 2), 2);

        when(service.pagedList(any(PageRequest.class))).thenReturn(userPagedList);

        String expected = "{\"content\":[{\"firstName\":\"John\",\"lastName\":\"Foo\",\"photo\":\"foo.jpg\"},{\"firstName\":\"Jane\",\"lastName\":\"Bar\",\"photo\":\"bar.jpg\"}],\"number\":0,\"size\":2,\"totalElements\":2,\"pageable\":{\"sort\":{\"unsorted\":true,\"sorted\":false,\"empty\":true},\"pageNumber\":0,\"pageSize\":2,\"offset\":0,\"paged\":true,\"unpaged\":false},\"last\":true,\"totalPages\":1,\"sort\":{\"unsorted\":true,\"sorted\":false,\"empty\":true},\"first\":true,\"numberOfElements\":2,\"empty\":false}";

        assertTrue(
                matchJson(
                        mockMvc.perform(get(ControllerConstants.V_1_USERS))
                                .andExpect(status().isOk())
                                .andReturn()
                                .getResponse()
                                .getContentAsString(),
                        expected
                )
        );

    }

    @Test
    void deleteUser_shouldRespond204() throws Exception {

        doNothing().when(service).deleteUser(eq(ID));

        mockMvc.perform(delete(ControllerConstants.V_1_USERS + "/" + ID))
                .andExpect(status().isNoContent());
    }
}
