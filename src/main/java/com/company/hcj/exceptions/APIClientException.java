package com.company.hcj.exceptions;

public class APIClientException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public APIClientException() {
	}

	public APIClientException(String message) {
		super(message);
	}

	public APIClientException(Throwable cause) {
		super(cause);
	}
}
