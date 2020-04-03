package com.tushar.expenses.periods.form;

import java.io.Serializable;

import com.tushar.expenses.periods.Money;
import com.tushar.expenses.periods.TransactionDate;
import com.tushar.expenses.periods.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PeriodForm implements Serializable {

	private TransactionDate startDate;
	private TransactionDate endDate;
	private TransactionDate transactionDate;
	private Money amount;
	private TransactionType transactionType;
	private String description;
	private String tag;
	private String userId;

	public String userId() {
		return userId;
	}
	
	
}
