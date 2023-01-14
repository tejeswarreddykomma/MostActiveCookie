package com.quantcast.demo.exception;

public class CookieException extends Exception{
    private String message;

    public CookieException() {
    
    }

    public CookieException(String message) {
		super();
		this.message = message;
	}

	public String getMessage(){
        return message;
    }

}
