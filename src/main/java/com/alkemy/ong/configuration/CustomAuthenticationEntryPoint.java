package com.alkemy.ong.configuration;

import com.alkemy.ong.enumeration.ApplicationErrorCode;
import com.alkemy.ong.enumeration.Location;
import com.alkemy.ong.exception.ErrorDetails;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;


public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
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

    }
}

