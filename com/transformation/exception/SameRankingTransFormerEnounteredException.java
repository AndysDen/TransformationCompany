package com.transformation.exception;

public class SameRankingTransFormerEnounteredException extends RuntimeException {
	
	private String message;
	
	public SameRankingTransFormerEnounteredException(String message ){
		super(message);
		this.message=message;
	}
	
	public String toString(){
		return super.toString()+"    "+this.getClass()+"::"+message;
	}

}
