package com.apirest01.exceptions;

public class StudentException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public StudentException(String ex) {
		super(ex);
	}
}
