package com.alkemy.ong.exception;

public class FileUploadException extends RuntimeException {
    private String message;

    public FileUploadException(String message) {
        super(message);
    }
}
