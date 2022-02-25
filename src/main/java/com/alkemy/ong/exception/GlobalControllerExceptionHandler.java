package com.alkemy.ong.exception;

import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
class GlobalControllerExceptionHandler {

	@ExceptionHandler(value = {NullFileException.class})
	public ResponseEntity<ErrorResponse> NullFileExceptionHandler(NullFileException ex) {
		ErrorResponse error = new ErrorResponse();
		error.setStatus(HttpStatus.SC_BAD_REQUEST);
		error.setMessage(ex.getMessage());
		error.setTimeStamp(ZonedDateTime.now());
		return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(error);
	}
}
