package com.tushar.expenses.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "User already exist")
public class UserAlreadyRegistered extends RuntimeException {

	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}

	
}
