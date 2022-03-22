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

import com.alkemy.ong.controller.ControllerConstants;
import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.dto.TestimonialPagedList;
import com.alkemy.ong.exception.GlobalControllerExceptionHandler;
import com.alkemy.ong.exception.TestimonialNotFoundException;
import com.alkemy.ong.service.TestimonialService;
import com.alkemy.ong.utils.JsonUtils;

@ExtendWith(MockitoExtension.class)
class TestimonialControllerTest {

    private MockMvc mockMvc;

    private final long ID = 0;
    
    @InjectMocks
    private TestimonialController controller;

    @Mock
    private TestimonialService service;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(GlobalControllerExceptionHandler.class)
                .build();
    }
    @Test
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
    void update_shouldRespond200() throws Exception {
    	
    	final TestimonialDto dto = TestimonialDto.builder()
                .name("John")
                .image("foo.jpg")
                .content("I´m John")
                .build();
        
        mockMvc.perform(put(ControllerConstants.V_1_TESTIMONIAL + "/" + ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(dto)))
                .andExpect(status().is(200))
                .andReturn();
    }
    @Test
    void update_shouldRespond400() throws Exception {
    	
    	final TestimonialDto dto = TestimonialDto.builder()
                .name("John")
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
    void update_shouldRespond404() throws Exception {
    	final TestimonialDto dto = TestimonialDto.builder()
                .name("John")
                .image("foo.jpg")
                .content("I´m John")
                .build();
        
    	doThrow(new TestimonialNotFoundException("Testimonial not found.")).when(service).testimonialPut(ID, dto);
    	
        mockMvc.perform(put(ControllerConstants.V_1_TESTIMONIAL + "/" + ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(dto)))
                .andExpect(status().isNotFound());
    }
    @Test
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
    void deleteTestimonial_shouldRespond204() throws Exception {

        doNothing().when(service).deleteTestimonial(eq(ID));

        mockMvc.perform(delete(ControllerConstants.V_1_TESTIMONIAL + "/" + ID))
                .andExpect(status().isNoContent());
    }
    @Test
    void deleteTestimonial_shouldRespond404() throws Exception {

    	doThrow(new TestimonialNotFoundException("Testimonial not found with id " + ID)).when(service).deleteTestimonial(ID);
        
        mockMvc.perform(delete(ControllerConstants.V_1_TESTIMONIAL + "/" + ID))
                .andExpect(status().is(404));
    }
}
