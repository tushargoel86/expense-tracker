package com.tushar.expenses.periods.form;

import com.tushar.expenses.periods.TransactionDate;
import com.tushar.expenses.user.UserId;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ListPeriodForm {

	private TransactionDate startDate;
	private TransactionDate endDate;
	private UserId userId;
	
}
