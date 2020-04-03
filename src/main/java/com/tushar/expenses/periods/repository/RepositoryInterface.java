package com.tushar.expenses.periods.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tushar.expenses.periods.Period;
import com.tushar.expenses.periods.PeriodId;
import com.tushar.expenses.periods.Tag;
import com.tushar.expenses.periods.TagId;
import com.tushar.expenses.periods.TransactionDate;
import com.tushar.expenses.user.User;

@Repository
public interface RepositoryInterface {
	
	public Optional<Period> findById(PeriodId id);

	public Period save(Period period);
	
	// and pd.periodId=tag.period and tag.tagId = tr.tag
	@Query("select pd, tra from Period pd, Tag tag, Transaction tra where pd.fromDate = :start and pd.toDate = :end and pd.user = :user and pd.periodId=tag.period and tag.tagId = tra.tag")
	public List<Object> findByFromDateAndToDate1(User user, TransactionDate start, TransactionDate end);

	@Query("select pd from Period pd where pd.user = :user and pd.fromDate = :startDate and pd.toDate = :endDate")
	public Period findByFromDateAndToDate(User user, TransactionDate startDate, TransactionDate endDate);

	public void deleteById(PeriodId pd);

	@Query("from Period where fromDate = :startDate and toDate = :endDate and user = :user")
	public Optional<Period> findByFromDateAndToDate2(User user, TransactionDate startDate, TransactionDate endDate);

	@Query("from Tag where tagName = :tagName and period = :period")
	public Tag findTagByNameAndPeriod(String tagName, Period period);
	
//	@Query(value = "select * from Tag tag, Transaction tr where tr.tag_id = tag.tag_id and tag.tag_id in (select tag_id from Tag where period_id = :periodId)", nativeQuery = true)
	//@Query("from Tag tag join fetch tag.transactions where tag.tagId in (select tag_id from Tag where period_id = :periodId)")
	//public  List<Tag> findByFromDateAndToDate(String periodId);

}
