package com.anz.fxcalculator.exception;

public class FxCaluclatorException extends Exception {

	private int errorCode;
	private String message;

	public FxCaluclatorException(int errorCode, Throwable cause, String message){
		super(cause);
		this.message = message;
		this.errorCode = errorCode;
	}
	
	public FxCaluclatorException(int errorCode, String message){
		super();
		this.errorCode = errorCode;
		this.message = message;
	}
	
	public FxCaluclatorException(int errorCode, Throwable cause){
		super(cause);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getMessage() {
		return message;
	}


}
