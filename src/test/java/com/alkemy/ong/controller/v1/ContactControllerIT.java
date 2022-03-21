package com.alkemy.ong.controller.v1;

import com.alkemy.ong.H2Config;
import com.alkemy.ong.SecurityConfig;
import com.alkemy.ong.controller.ControllerConstants;
import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.service.ContactService;
import com.alkemy.ong.utils.JsonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static com.alkemy.ong.utils.TestUtils.matchJson;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringJUnitConfig(classes = {H2Config.class, SecurityConfig.class})
class ContactControllerIT {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	private ContactService service;

	@Test
	void saveContact_shouldRespond201() throws Exception {
		final ContactDto contact = ContactDto.builder()
				.id(1L)
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

		List<ContactDto> contactDtoList = new ArrayList<ContactDto>();

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