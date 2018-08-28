package com.fionapet.tenant.exception;

public class RoleAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public RoleAlreadyExistsException(String nome) {
		super(nome);
	}
	
}
