package com.alkemy.ong.controller.v1;

import com.alkemy.ong.controller.ControllerConstants;
import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.exception.GlobalControllerExceptionHandler;
import com.alkemy.ong.service.ContactService;
import com.alkemy.ong.utils.JsonUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static com.alkemy.ong.utils.TestUtils.matchJson;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
class ContactControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private ContactController controller;

	@Mock
	private ContactService service;

	static final long ID = 123;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.setControllerAdvice(GlobalControllerExceptionHandler.class)
				.build();
	}

	@Test
	void saveContact_shouldRespond201() throws Exception {
		final ContactDto contact = ContactDto.builder()
				.id(ID)
				.name("John")
				.phone("(362)4789909")
				.email("john@john.com")
				.message("message")
				.build();

		mockMvc.perform(post(ControllerConstants.V_1_CONTACTS)
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonUtils.objectToJson(contact)))
				.andExpect(status().isCreated());
	}

	@Test
	void saveContact_shouldRespond400() throws Exception {
		final ContactDto contact = ContactDto.builder()
				.name("")
				.phone("")
				.email("john@john.com")
				.message("")
				.build();

		mockMvc.perform(post(ControllerConstants.V_1_CONTACTS)
						.contentType(MediaType.APPLICATION_JSON)
						.content(JsonUtils.objectToJson(contact)))
				.andExpect(status().isBadRequest());
	}

	@Test
	void listContact_shouldRespond200() throws Exception{
		final ContactDto contact1 = ContactDto.builder()
				.id(1L)
				.name("John")
				.phone("(362)5555555")
				.email("john@john.com")
				.message("message")
				.build();
		final ContactDto contact2 = ContactDto.builder()
				.id(2L)
				.name("Doe")
				.phone("(362)5555556")
				.email("doe@doe.com")
				.message("message")
				.build();

		List<ContactDto> contactDtoList = new ArrayList<>();

		contactDtoList.add(contact1);
		contactDtoList.add(contact2);
		when(service.getAll()).thenReturn(contactDtoList);

		String expected = "[{\"id\":1,\"name\":\"John\",\"phone\":\"(362)5555555\",\"email\":\"john@john.com\",\"message\":\"message\"}," +
				"{\"id\":2,\"name\":\"Doe\",\"phone\":\"(362)5555556\",\"email\":\"doe@doe.com\",\"message\":\"message\"}]";

		assertTrue(
				matchJson(
						mockMvc.perform(get(ControllerConstants.V_1_CONTACTS))
								.andExpect(status().isOk())
								.andReturn()
								.getResponse()
								.getContentAsString(),
						expected
				)
		);
	}
}