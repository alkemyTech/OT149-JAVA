package com.alkemy.ong.exception;

import com.alkemy.ong.dto.UserNotFoundErrorDTO;
import com.alkemy.ong.enumeration.ApplicationErrorCode;
import com.alkemy.ong.enumeration.Location;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.Arrays;
import java.util.List;


@ControllerAdvice
public class GlobalControllerExceptionHandler extends AbstractExceptionHandler {

    /**
     * Este metodo se encarga de la captura del error USER_NOT_FOUND
     *
     * @return Devuelve la excepcion que estoy capturando y el dto
     */
    @ExceptionHandler(value = {UserNotFoundException.class})
    protected ResponseEntity<Object> handleUserNotFound(RuntimeException ex, WebRequest request) {

        UserNotFoundErrorDTO errorDTO = new UserNotFoundErrorDTO(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                Arrays.asList("User not found")
        );

        return super.handleExceptionInternal(ex, new HttpHeaders(), HttpStatus.NOT_FOUND, request, ApplicationErrorCode.NOT_FOUND, errorDTO.getMessage());
    }


    @ExceptionHandler(value = {BadUserLoginException.class})
    protected void handleBadUserLoginException(HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(401);
        JsonGenerator jsonGenerator = Json.createGenerator(response.getWriter());
        jsonGenerator.writeStartObject()
                .write("ok", false)
                .writeEnd()
                .close();
    }

    @ExceptionHandler(value = {TestimonialNotFoundException.class, NotFoundException.class})
    protected ResponseEntity<ErrorDetails> handleTestimonialNotFoundException(RuntimeException exc) {

        ErrorDetails error = ErrorDetails.builder()
                .code(ApplicationErrorCode.NOT_FOUND)
                .description(exc.getMessage())
                .field("id")
                .location(Location.PATH)
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(value = {NewNotFoundException.class})
    protected ResponseEntity<Object> handleNewNotFound(RuntimeException ex, WebRequest request) {

        ErrorDetails error1 = ErrorDetails.builder()
                .code(ApplicationErrorCode.NOT_FOUND)
                .description(ApplicationErrorCode.NOT_FOUND.getDefaultMessage())
                .field("id")
                .location(Location.PATH)
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of(error1));

    }

}
