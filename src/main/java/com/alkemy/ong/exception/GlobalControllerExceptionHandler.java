package com.alkemy.ong.exception;

import com.alkemy.ong.dto.UserNotFoundErrorDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZonedDateTime;
import java.util.Arrays;

@ControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {
  
    /**
     * Este metodo se encarga de la captura del error USER_NOT_FOUND
     * @return Devuelve la excepcion que estoy capturando y el dto
     */
    @ExceptionHandler(value = {UserNotFoundException.class})
    protected ResponseEntity<Object> handleUserNotFound(RuntimeException ex, WebRequest request){

        UserNotFoundErrorDTO errorDTO = new UserNotFoundErrorDTO(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                Arrays.asList("User not found")
        );

        return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
  
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exc){

        ErrorResponse error = new ErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getFieldError().getDefaultMessage());
        error.setTimeStamp(ZonedDateTime.now());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
  
}