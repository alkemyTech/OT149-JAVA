package com.alkemy.ong.controller.v1;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import com.alkemy.ong.H2Config;
import com.alkemy.ong.SecurityConfig;
import com.alkemy.ong.controller.ControllerConstants;
import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.dto.TestimonialPagedList;
import com.alkemy.ong.exception.TestimonialNotFoundException;
import com.alkemy.ong.service.TestimonialService;
import com.alkemy.ong.utils.JsonUtils;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringJUnitConfig(classes = {H2Config.class, SecurityConfig.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestimonialControllerIT {

    @Autowired
    MockMvc mockMvc;

    @Mock
    private TestimonialService service;
    
    static final long ID = 1;
    
    @Test
    @Order(1)
    void createTestimonial_shouldRespond201() throws Exception {

        final TestimonialDto dto = TestimonialDto.builder()
                .name("John")
                .image("foo.jpg")
                .content("I´m John")
                .build();

        mockMvc.perform(post(ControllerConstants.V_1_TESTIMONIAL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(dto)))
                		.andExpect(status().isCreated());
    }
    @Order(2)
    @Test
    void createTestimonial_shouldRespond400() throws Exception {

        final TestimonialDto dto = TestimonialDto.builder()
                .name("")
                .image("foo.jpg")
                .content("I´m John")
                .build();

        mockMvc.perform(post(ControllerConstants.V_1_TESTIMONIAL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(dto)))
                		.andExpect(status().isBadRequest());
    }
    @Test
    @Order(3)
    void list_shouldRespond200() throws Exception {
    	
    	final TestimonialDto dto1 = TestimonialDto.builder()
                .name("John")
                .image("foo.jpg")
                .content("I´m John")
                .build();
    	
    	final TestimonialDto dto2 = TestimonialDto.builder()
                .name("Peter")
                .image("duu.jpg")
                .content("I´m Peter")
                .build();
    	
    	TestimonialPagedList testimonialPagedList = new TestimonialPagedList(List.of(dto1, dto2), PageRequest.of(0, 2), 2);

        
        mockMvc.perform(get(ControllerConstants.V_1_TESTIMONIAL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(testimonialPagedList)))
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    @Order(4)
    void update_shouldRespond200() throws Exception {
    	
    	final TestimonialDto dto = TestimonialDto.builder()
                .name("Carl")
                .image("foo.jpg")
                .content("I´m Carl")
                .build();
        
        mockMvc.perform(put(ControllerConstants.V_1_TESTIMONIAL + "/" + ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(dto)))
                .andExpect(status().is(200))
                .andReturn();
    }
    @Test
    @Order(5)
    void update_shouldRespond400() throws Exception {
    	
    	final TestimonialDto dto = TestimonialDto.builder()
                .name("Carl")
                .image("foo.jpg")
                .content("")
                .build();
        
        mockMvc.perform(put(ControllerConstants.V_1_TESTIMONIAL + "/" + ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(dto)))
                .andExpect(status().is(400))
                .andReturn();
    }
    @Test
    @Order(6)
    void update_shouldRespond404() throws Exception {
    	final TestimonialDto dto = TestimonialDto.builder()
                .name("John")
                .image("foo.jpg")
                .content("I´m John")
                .build();
            	
        mockMvc.perform(put(ControllerConstants.V_1_TESTIMONIAL + "/122")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(dto)))
                .andExpect(status().isNotFound());
    }
    
    
    
    @Test
    @Order(7)
    void deleteTestimonial_shouldRespond204() throws Exception {

        doNothing().when(service).deleteTestimonial(eq(ID));

        mockMvc.perform(delete(ControllerConstants.V_1_TESTIMONIAL + "/" + ID))
                .andExpect(status().isNoContent());
    }
    @Test
    @Order(8)
    void deleteTestimonial_shouldRespond404() throws Exception {
        
        mockMvc.perform(delete(ControllerConstants.V_1_TESTIMONIAL + "/122"))
                .andExpect(status().is(404));
    }
    
    
}
