package com.projet.eval.exception;

public class AuthException  extends Exception {
	String message;
	public AuthException(){}
	public AuthException(String message){
		this.message=message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
