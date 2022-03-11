package com.alkemy.ong.exception;

public class BadUserLoginException extends RuntimeException{
    public BadUserLoginException(String msg){
        super(msg);
    }
}
