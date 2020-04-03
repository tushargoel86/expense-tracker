package com.tushar.expenses.periods;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tushar.expenses.exception.ExpenseDateOutOfRangeException;
import com.tushar.expenses.user.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Period implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "period_id")
	@Type(type = "com.tushar.expenses.periods.PeriodIdType")
	private PeriodId periodId;

	@JsonBackReference
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "fromDate")
	@Type(type = "com.tushar.expenses.periods.TransactionDateType")
	private TransactionDate fromDate;

	@Column(name = "toDate")
	@Type(type = "com.tushar.expenses.periods.TransactionDateType")
	private TransactionDate toDate;

//	@JsonManagedReference
//	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST,
//			CascadeType.REFRESH }, orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "period")
//	private Set<Tag> tags;

	@JsonManagedReference
	@OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER, mappedBy = "period")
	private Set<Tag> tags;	
	
	public Period(User user, TransactionDate fromDate, TransactionDate toDate) {
		this.user = user;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.periodId = new PeriodId(UUID.randomUUID().toString());
		this.tags = new HashSet<>();
	}

	public void recordExpensesOrReceived(Tag tag, TransactionType transactionType, String description, Money money,
			TransactionDate date) {

		if (!date.expenseDateWithinPeriodDateRange(fromDate, toDate))
			throw new ExpenseDateOutOfRangeException("Expense must be within current period date range!");

		if (transactionType == TransactionType.EXPENSE)
			tag.recordExpenseAndModifyAllocation(description, date, money);
		else
			tag.recordRecivedAndModifyAllocation(description, date, money);

		tags.add(tag);
	}
	

	public void updateExistingExpense(Transaction txId, String tagId, TransactionType transactionType,
			String description, Money money, TransactionDate date) {
		// find old tag with the txid
		// find tag from tag id
		// if both same than update trx in same tag
		// else add trx in new tag and remove it from old tag
	}

	public void removeTransaction(Tag tag, TransactionId transactionId) {
//		Tag tag = getTag(tagId);
//		tag.removeTransaction(transactionId);
	}

}
