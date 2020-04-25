package com.tushar.expenses.periods.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tushar.expenses.periods.Period;
import com.tushar.expenses.periods.PeriodId;
import com.tushar.expenses.periods.Tag;
import com.tushar.expenses.periods.TransactionDate;
import com.tushar.expenses.user.User;

@Repository
public interface RepositoryInterface {
	
	public Optional<Period> findById(PeriodId id);

	public Period save(Period period);
	
	public void deleteById(PeriodId pd);

	@Query("from Period where fromDate = :startDate and toDate = :endDate and user = :user")
	public Optional<Period> findByFromDateAndToDate(User user, TransactionDate startDate, TransactionDate endDate);

	@Query("from Tag where tagName = :tagName and period = :period")
	public Tag findTagByNameAndPeriod(String tagName, Period period);
}
