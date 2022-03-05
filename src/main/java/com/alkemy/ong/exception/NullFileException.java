package com.alkemy.ong.exception;

public class NullFileException extends RuntimeException {
	private String message;
	public NullFileException(String message){
		super(message);
	}
}
