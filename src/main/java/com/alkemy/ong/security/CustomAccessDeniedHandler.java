package com.alkemy.ong.security;

import com.alkemy.ong.enumeration.ApplicationErrorCode;
import com.alkemy.ong.enumeration.Location;
import com.alkemy.ong.exception.ErrorDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse response, AccessDeniedException e) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(403);
        ErrorDetails error = ErrorDetails.builder()
                .code(ApplicationErrorCode.ROLE_INVALID)
                .description(ApplicationErrorCode.ROLE_INVALID.getDefaultMessage())
                .field("Authorization")
                .location(Location.HEADER)
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonError = objectMapper.writeValueAsString(error);
        response.getWriter().write(jsonError);
    }
}
