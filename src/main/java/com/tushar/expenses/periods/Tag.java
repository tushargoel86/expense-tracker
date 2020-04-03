package com.tushar.expenses.periods;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tag")
public class Tag implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "tag_id")
	@Type(type = "com.tushar.expenses.periods.TagIdType")
	private TagId tagId;

	@Column(name="tag_name")
	private String tagName;

//	@JsonManagedReference
//	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST,
//			CascadeType.REFRESH }, orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "tag")
//	private List<Transaction> transactions;
	
	@JsonManagedReference
	@OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER, mappedBy = "tag")
	private List<Transaction> transactions;	

	
	@JsonBackReference
//	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "period_id")
	private Period period;
	
	@Column
	private int tagAmount;

	public Tag(String tagName) {
		this.tagName = tagName;
		this.tagId = new TagId(UUID.randomUUID().toString());
		this.transactions = new ArrayList<>();
	}

	public void recordExpenseAndModifyAllocation(String description, TransactionDate date, Money money) {

		Transaction tranx = new Transaction(money, description, date, TransactionType.EXPENSE, this);

		transactions.add(tranx);

		tagAmount = new Money(tagAmount).subtract(money).fixedPointAmount();
	}

//	public void recordAndUpdateExpense(TransactionType type, String description, TransactionDate date, Money money) {
//		if (type.equals(TransactionType.RECEIVED))
//			recordRecivedAndModifyAllocation(description, date, money);
//		else 
//			recordExpenseAndModifyAllocation(description, date, money);
//	}
	
	public void recordRecivedAndModifyAllocation(String description, TransactionDate date, Money money) {

		// create transaction
		Transaction tranx = new Transaction(money, description, date, TransactionType.RECEIVED, this);
		
		tranx.setTag(this);

		// add transaction in tag
		transactions.add(tranx);

		tagAmount = new Money(tagAmount).add(money).fixedPointAmount();
	}

	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (o == null || o.getClass() != getClass()) {
			return false;
		}
		return tagName.equals(((Tag) o).tagName);
	}

	public int hashCode() {
		return Objects.hash(tagName);
	}

	public String tag() {
		return tagName;
	}

	public void removeTransaction(TransactionId transactionId) {
		// TODO Auto-generated method stub

	}

	public TagId id() {
		return tagId;
	}

	public void addPeriod(Period period) {
		this.period = period;
	}
}
