package com.alkemy.ong.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.SERVICE_UNAVAILABLE, reason="The server is unavailable to handle this request right now. Please try again later.")
public class ServiceUnavailableException extends RuntimeException {
}
