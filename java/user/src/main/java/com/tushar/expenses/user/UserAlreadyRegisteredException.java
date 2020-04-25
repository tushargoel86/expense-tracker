package com.tushar.expenses.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "User already exist")
public class UserAlreadyRegisteredException extends RuntimeException {

	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}

	
}
