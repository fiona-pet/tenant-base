package com.fionapet.tenant.exception;

public class RoleNotExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public RoleNotExistsException(String nome) {
		super(nome);
	}
	
}
