package com.alkemy.ong.configuration;

import com.alkemy.ong.enumeration.ApplicationErrorCode;
import com.alkemy.ong.enumeration.Location;
import com.alkemy.ong.exception.ErrorDetails;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.json.Json;
//import javax.json.stream.JsonGenerator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(403);
        ErrorDetails error = ErrorDetails.builder()
                .code(ApplicationErrorCode.TOKEN_INVALID_OR_EXPIRED)
                .description(ApplicationErrorCode.TOKEN_INVALID_OR_EXPIRED.getDefaultMessage())
                .field("Authorization")
                .location(Location.HEADER)
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonError = objectMapper.writeValueAsString(error);
        response.getWriter().write(jsonError);
        /*OutputStream outputStream = new ByteArrayOutputStream();
        JsonFactory factory=new JsonFactory();
        JsonGenerator generator = factory.createGenerator(response.getWriter());

        generator.writeStartObject();
        generator.writeFieldName("null");



        generator.writeEndObject();*/
    }
}

