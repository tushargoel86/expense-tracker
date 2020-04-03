package com.tushar.expenses.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.PRECONDITION_FAILED, reason = "Expense is not in range")
public class ExpenseDateOutOfRangeException extends RuntimeException {
	
	public ExpenseDateOutOfRangeException(String msg) {
		super(msg);
	}

	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}
}
