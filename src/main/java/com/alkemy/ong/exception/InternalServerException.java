package com.alkemy.ong.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR, reason="The server is unavailable to handle this request due an internal error.")
public class InternalServerException extends RuntimeException {
}
