package com.tushar.expenses.periods;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;

@Getter
public class TransactionDate implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final LocalDate transactionDate;
	
	@JsonIgnore
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public TransactionDate(String transactionDate) {
		this.transactionDate = LocalDate.parse(transactionDate, formatter); // @formatter:off
	}
	

	public boolean expenseDateWithinPeriodDateRange(TransactionDate fromDate, TransactionDate toDate) {
		return transactionDate.isAfter(fromDate.getTransactionDate()) && transactionDate.isBefore(toDate.getTransactionDate());
	}
	
	@Override
	public String toString() {
		return transactionDate.toString();
	}
	
}
