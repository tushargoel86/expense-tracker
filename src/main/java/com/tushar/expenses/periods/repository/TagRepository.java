package com.tushar.expenses.periods.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tushar.expenses.periods.Period;
import com.tushar.expenses.periods.Tag;
import com.tushar.expenses.periods.TagId;

@Repository
public interface TagRepository extends JpaRepository<Tag, TagId> {

	@Query("from Tag tag LEFT JOIN FETCH tag.transactions where tag.tagId in (select tagId from Tag tg where tg.period = :period)")
	public Tag findByPeriodId(Period period);

//	@Query("from Tag tag LEFT JOIN FETCH tag.transactions where tag.tagId = (select tr.tag from Transaction tr where tr.tag = :tag)")
	@Query("from Tag tag LEFT JOIN FETCH tag.transactions where tag.tagId = (select tr.tag from Transaction tr where tr.tag = :tag)")
	public Optional<Tag> findByTagId(Tag tag);
}
