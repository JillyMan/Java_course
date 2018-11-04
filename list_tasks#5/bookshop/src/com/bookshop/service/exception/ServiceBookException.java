package com.bookshop.service.exception;

@SuppressWarnings("serial")
public class ServiceBookException extends Exception {
	public ServiceBookException () { 
		
	}
	
	public ServiceBookException (String message) { 
		super(message);
	}
}
