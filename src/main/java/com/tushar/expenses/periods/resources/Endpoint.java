package com.tushar.expenses.periods.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tushar.expenses.periods.Money;
import com.tushar.expenses.periods.Period;
import com.tushar.expenses.periods.TransactionDate;
import com.tushar.expenses.periods.TransactionType;
import com.tushar.expenses.periods.form.DeletePeriodForm;
import com.tushar.expenses.periods.form.DeleteRequest;
import com.tushar.expenses.periods.form.FormRequest;
import com.tushar.expenses.periods.form.ListPeriodForm;
import com.tushar.expenses.periods.form.ListRequest;
import com.tushar.expenses.periods.form.PeriodForm;
import com.tushar.expenses.periods.service.PeriodService;
import com.tushar.expenses.user.UserId;

@RestController
public class Endpoint {

	@Autowired
	private PeriodService service;

	@GetMapping("/welcome")
	public String welcome() {
		return "Hello, World!";
	}

	@PostMapping("/expense")
	public void addExpense(@RequestBody FormRequest request) {
		service.recordTransaction(new PeriodForm(new TransactionDate(request.getStartDate()),
				new TransactionDate(request.getEndDate()), new TransactionDate(request.getTransactionDate()),
				new Money(request.getAmount()), TransactionType.valueOf(request.getTransactionType()),
				request.getDescription(), request.getTag(), request.getUserId()));
	}

	@PostMapping("/")
	public Period listExpensesFor(@RequestBody ListRequest request) {
		return service.periodFor(new ListPeriodForm(new TransactionDate(request.getStartDate()),
								 new TransactionDate(request.getEndDate()),
								 new UserId(request.getUserId())));
	}
	
	@DeleteMapping("/")
	@ResponseStatus(value = HttpStatus.OK)
	public void deletePeriodFor(@RequestBody DeleteRequest request) {
		service.deleteperiodFor(new DeletePeriodForm(new TransactionDate(request.getStartDate()),
								 new TransactionDate(request.getEndDate()),
								 new UserId(request.getUserId())));
	}
}
