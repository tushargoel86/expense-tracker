package com.tushar.expenses.periods.service;

import java.util.Optional;

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
import com.tushar.expenses.user.User;
import com.tushar.expenses.user.UserId;
import com.tushar.expenses.user.UserRepository;


@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class PeriodService {

	private RepositoryInterface periodRepository;
	
	private UserRepository userRepository;
	
	public PeriodService(RepositoryInterface periodRepository, UserRepository userRepository) {
		this.periodRepository = periodRepository;
		this.userRepository = userRepository;
	}

	public void recordTransaction(PeriodForm form) {
		Optional<User> user = userRepository.findByUserId(new UserId(form.userId()));
		if (user.isPresent()) {
			Optional<Period> pd = periodRepository.findByFromDateAndToDate(user.get(), form.getStartDate(), form.getEndDate());
			Period period = pd.isPresent() ? pd.get() : new Period(user.get(), form.getStartDate(), form.getEndDate());
		
			Tag tag = periodRepository.findTagByNameAndPeriod(form.getTag(), period);
			if (tag == null)
				tag = new Tag(form.getTag());
			tag.setPeriod(period);
			
			period.recordExpensesOrReceived(tag, form.getTransactionType(), form.getDescription(), form.getAmount(), form.getTransactionDate());
			periodRepository.save(period);
		} else {
			throw new UserNotFoundException("user with id " + form.userId() + " not found.");
		}
		
	}

	public Period periodFor(ListPeriodForm form) {
		Optional<User> user = userRepository.findByUserId(form.getUserId());
		if (user.isPresent()) {
			Optional<Period> period = periodRepository.findByFromDateAndToDate(user.get(), form.getStartDate(), form.getEndDate());
			if (period.isPresent()) {
				Period pd = period.get();
				return pd;
			}
			throw new NoDataFoundException();
		} else {
			throw new UserNotFoundException("user with id " + form.getUserId() + " not found.");
		}
	}

	public void deleteperiodFor(DeletePeriodForm form) {
		Optional<User> user = userRepository.findByUserId(form.getUserId());
		if (user.isPresent()) {
			Optional<Period> pd = periodRepository.findByFromDateAndToDate(user.get(), form.getStartDate(), form.getEndDate());
			if (pd.isPresent())
				periodRepository.deleteById(pd.get().getPeriodId());
			else throw new NoDataFoundException();
		} else {
			throw new UserNotFoundException("user with id " + form.getUserId() + " not found.");
		}
	}
	
	
	
}
