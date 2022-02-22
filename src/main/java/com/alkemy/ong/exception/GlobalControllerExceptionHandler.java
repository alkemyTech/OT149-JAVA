package com.alkemy.ong.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

	@ExceptionHandler(value={ResourceNotFoundException.class})
	public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException exception){
		ErrorResponse error= new ErrorResponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exception.getMessage());
		error.setTimeStamp(ZonedDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(Exception  exception){
		ErrorResponse error = new ErrorResponse();

		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(exception.getMessage());
		error.setTimeStamp(ZonedDateTime.now());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
