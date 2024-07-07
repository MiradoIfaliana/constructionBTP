package com.projet.eval.exception;

public class MyException  extends Exception {
	String message;
	public MyException(){}
	public MyException(String message){
		this.message=message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
