package com.tushar.expenses.periods.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListRequest {
	private String startDate;
	private String endDate;
	private String userId;
}
