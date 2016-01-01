package com.frame.exception;

import javax.servlet.ServletException;

public class AuthorizationException extends ServletException {

	public AuthorizationException(String message) {
		super(message);
	}

}
