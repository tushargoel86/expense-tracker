package com.tushar.expenses.user;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tushar.expenses.periods.Period;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Getter
@Setter
@Table(name = "tbl_user", uniqueConstraints = {
		@UniqueConstraint(columnNames = "email", name="uniqueEmailContraint")
})
public class User {

	@Id
	@Column(name = "user_id")
	@Type(type = "com.tushar.expenses.user.UserIdType")
	private UserId userId;
	
	@Column
	private String userName;

	@Column
	private String email;
	
	@JsonManagedReference
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
	//@JoinColumn(name = "periodId")
	private List<Period> periods;

	
	public User() {
		
	}

	public User(UserForm userForm) {
		Objects.requireNonNull(userForm, "user form is required");
		setupName(Objects.requireNonNull(userForm.getUserName(), "user name is required"));
		setupEmail(Objects.requireNonNull(userForm.getEmail(), "email is required"));
		this.userId = new UserId(UUID.randomUUID().toString());
	}

	private void setupEmail(UserEmail userEmail) {
		this.email = userEmail.getEmail();
	}

	private void setupName(Name name) {
		this.userName = name.getName();
	}

}
