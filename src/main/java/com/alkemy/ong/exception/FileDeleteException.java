package com.alkemy.ong.exception;

public class FileDeleteException extends RuntimeException{
    private String message;

    public FileDeleteException(String message){
        super(message);
    }
}
