package com.github.bobocodeProjects.exception;

public class AccountNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 8520900772749492186L;

	public AccountNotFoundException(String message) {
        super(message);
    }
}
