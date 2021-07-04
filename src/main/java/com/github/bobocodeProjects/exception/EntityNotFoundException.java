package com.github.bobocodeProjects.exception;

public class EntityNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5275469431020389886L;

	public EntityNotFoundException(String message) {
        super(message);
    }
}
