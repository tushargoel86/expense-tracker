package com.tushar.expenses.periods.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tushar.expenses.periods.Period;
import com.tushar.expenses.periods.PeriodId;


public interface PeriodRepository extends RepositoryInterface, JpaRepository<Period, PeriodId> {

}
