package com.bookshop.service.exception;

@SuppressWarnings("serial")
public class ServiceOrderException extends Exception {
	public ServiceOrderException () { 
		
	}
	
	public ServiceOrderException (String message) { 
		super(message);
	}
}
