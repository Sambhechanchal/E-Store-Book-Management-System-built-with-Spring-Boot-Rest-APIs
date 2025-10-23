package com.nt.exception;

public class BookIdNotFoundException extends RuntimeException {
	
	public BookIdNotFoundException() {
	
	}
	
	public BookIdNotFoundException(String msg) {
		super(msg);
	}

}
