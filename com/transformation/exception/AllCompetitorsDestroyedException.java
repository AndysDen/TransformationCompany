package com.transformation.exception;

public class AllCompetitorsDestroyedException extends RuntimeException {
	
	private String message;
	
	public AllCompetitorsDestroyedException(String message){
		super(message);
		this.message=message;
	}
	
	
	public String toString(){
		return super.getMessage()+"::"+message;
	}

}
