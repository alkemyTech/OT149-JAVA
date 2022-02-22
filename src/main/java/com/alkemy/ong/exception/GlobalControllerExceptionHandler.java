package com.alkemy.ong.exception;

import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(value={FileUploadException.class})
    public ResponseEntity<ErrorResponse> handleFileUploadException(FileUploadException ex){
        ErrorResponse error= new ErrorResponse();
        error.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        error.setMessage(ex.getMessage());
        error.setTimeStamp(ZonedDateTime.now());
        return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(value={InvalidValueException.class})
    public ResponseEntity<ErrorResponse> handleInvalidValueException(InvalidValueException ex){
        ErrorResponse error= new ErrorResponse();
        error.setStatus(HttpStatus.SC_BAD_REQUEST);
        error.setMessage(ex.getMessage());
        error.setTimeStamp(ZonedDateTime.now());
        return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(error);
    }

    @ExceptionHandler(value={FileDeleteException.class})
    public ResponseEntity<ErrorResponse> handleFileDeleteException(FileDeleteException ex){
        ErrorResponse error= new ErrorResponse();
        error.setStatus(HttpStatus.SC_BAD_REQUEST);
        error.setMessage(ex.getMessage());
        error.setTimeStamp(ZonedDateTime.now());
        return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(error);
    }
}
