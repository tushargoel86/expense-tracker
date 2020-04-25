package com.tushar.expenses.periods;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
	@Getter
@Setter
@NoArgsConstructor
@Table(name="transaction")
public class Transaction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Type(type = "com.tushar.expenses.periods.TransactionIdType")
	@Column(name = "transaction_id")
	private TransactionId transactionId;
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	//private int transactionId;
	
	@Column(name="amount")
	private int money;

	@Column(name = "description")
	private String description;

	@Column(name = "date")
	@Type(type = "com.tushar.expenses.periods.TransactionDateType")
	private TransactionDate transactionDate;

	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;
	
	@ToString.Exclude
	@JsonBackReference
//	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
//	@JoinColumn(name = "tag_id")
//	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "tag_id")
	private Tag tag;
	
	public Transaction(Money money, String description,
			TransactionDate transactionDate, TransactionType transactionType, Tag tag) {
		this.transactionId = new TransactionId(UUID.randomUUID().toString());
		this.money = money.fixedPointAmount();
		this.description = description;
		this.transactionDate = transactionDate;
		this.transactionType = transactionType;
		this.tag = tag;
	}

	
	public void changeDescription(String description) {
		Objects.requireNonNull(description);
		this.description = description;
	}
	
	public void changeTransactionDate(TransactionDate transactionDate) {
		Objects.requireNonNull(transactionDate);
		this.transactionDate = transactionDate;
	}	
	
	public void changeTransactionAmount(Money money) {
		Objects.requireNonNull(money);
		this.money = money.fixedPointAmount();
	}
	
	public void changeTag(Tag tag) {
		Objects.requireNonNull(tag);
		this.tag = tag;
	}	

	public void changeTransactionType(TransactionType transactionType) {
		Objects.requireNonNull(transactionType);
		this.transactionType = transactionType;
	}
	
    
}
