package com.revature.dkwte.exception;

public class InvalidLoginException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidLoginException() {
		super();
	}

	public InvalidLoginException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidLoginException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidLoginException(String message) {
		super(message);
	}

	public InvalidLoginException(Throwable cause) {
		super(cause);
	}

}
