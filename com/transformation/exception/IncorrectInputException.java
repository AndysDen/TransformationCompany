package com.transformation.exception;

public class IncorrectInputException extends RuntimeException{
	
	private  String message; 
	
	public IncorrectInputException(String message){
		super(message);
		this.message =message;
	}
	
	
	public String toString(){
		return super.toString()+"    "+this.getClass()+"::"+message;
	}

}
