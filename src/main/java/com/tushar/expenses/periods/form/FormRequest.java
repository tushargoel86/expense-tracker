package com.tushar.expenses.periods.form;

import lombok.Data;

@Data
public class FormRequest {

	private String startDate;
	private String endDate;
	private String transactionDate;
	private int amount;
	private String transactionType;
	private String description;
	private String tag;
	private String userId;
}
