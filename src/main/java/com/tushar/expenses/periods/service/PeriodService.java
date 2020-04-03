package com.tushar.expenses.periods.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tushar.expenses.exception.NoDataFoundException;
import com.tushar.expenses.exception.UserNotFoundException;
import com.tushar.expenses.periods.Period;
import com.tushar.expenses.periods.Tag;
import com.tushar.expenses.periods.form.DeletePeriodForm;
import com.tushar.expenses.periods.form.ListPeriodForm;
import com.tushar.expenses.periods.form.PeriodForm;
import com.tushar.expenses.periods.repository.RepositoryInterface;
import com.tushar.expenses.periods.repository.TagRepository;
import com.tushar.expenses.user.User;
import com.tushar.expenses.user.UserId;
import com.tushar.expenses.user.UserRepository;


@Service
//@Transactional(propagation = Propagation.REQUIRES_NEW)
public class PeriodService {

	@Autowired
	private RepositoryInterface periodRepository;
	
	@Autowired
	private UserRepository userRepository;
	private TagRepository tagRepository;
//	
//	public PeriodService(RepositoryInterface periodRepository, UserRepository userRepository, TagRepository tagRepository) {
//		this.periodRepository = periodRepository;
//		this.userRepository = userRepository;
//		this.tagRepository = tagRepository;
//	}

	public void recordTransaction(PeriodForm form) {
		Optional<User> user = userRepository.findByUserId(new UserId(form.userId()));
		if (user.isPresent()) {
			Optional<Period> pd = periodRepository.findByFromDateAndToDate2(user.get(), form.getStartDate(), form.getEndDate());
			Period period = pd.isPresent() ? pd.get() : new Period(user.get(), form.getStartDate(), form.getEndDate());
			Tag tag = periodRepository.findTagByNameAndPeriod(form.getTag(), period);
			if (tag == null)
				tag = new Tag(form.getTag());
			tag.setPeriod(period);
			period.recordExpensesOrReceived(tag, form.getTransactionType(), form.getDescription(), form.getAmount(), form.getTransactionDate());
			Set<Tag> tags = period.getTags();
			
			tags.stream().forEach(tg -> tg.getTransactions().size());
			System.out.println("TagSize: " + tags.size());
			System.out.println("TranSize: " + tags);
			periodRepository.save(period);
		} else {
			throw new UserNotFoundException("user with id " + form.userId() + " not found.");
		}
		
	}

	public Period periodFor(ListPeriodForm form) {
		Optional<User> user = userRepository.findByUserId(form.getUserId());
		if (user.isPresent()) {
			Period period = periodRepository.findByFromDateAndToDate(user.get(), form.getStartDate(), form.getEndDate());
			Set<Tag> tags = period.getTags();
			for (Tag tag : tags) {
				System.out.println("period id: " + period.getPeriodId());
			//	Optional<Tag>  pd = tagRepository.findByTagId(tag);
		//		System.out.println("tag id: " + pd.get().getTagId());
				System.out.println(tag.getTransactions());
			}
			if (period != null) {
//				Period period  = (Period) pd;
//				System.out.println("fetching period.....................");
//				//Period period = pd.get();
//				for(Tag tag : period.getTags()) {
//					System.out.println("fetching tag.....................");
//					tag.setTransactions(tag.getTransactions());
//					System.out.println(tag.getTransactions());
//				}
				return period;
			}
		//	}
				
			throw new NoDataFoundException();
		} else {
			throw new UserNotFoundException("user with id " + form.getUserId() + " not found.");
		}
	}

	public void deleteperiodFor(DeletePeriodForm form) {
		Optional<User> user = userRepository.findByUserId(form.getUserId());
		if (user.isPresent()) {
			Period pd = periodRepository.findByFromDateAndToDate(user.get(), form.getStartDate(), form.getEndDate());
			if (pd != null)
				periodRepository.deleteById(pd.getPeriodId());
			else throw new NoDataFoundException();
		} else {
			throw new UserNotFoundException("user with id " + form.getUserId() + " not found.");
		}
	}
	
	
	
}
