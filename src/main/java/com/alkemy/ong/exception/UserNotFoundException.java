package com.alkemy.ong.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String error) {
        super(error);
    }
}
