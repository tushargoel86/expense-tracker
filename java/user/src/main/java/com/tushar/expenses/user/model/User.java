package com.tushar.expenses.user.model;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tushar.expenses.user.Name;
import com.tushar.expenses.user.UserEmail;
import com.tushar.expenses.user.UserForm;
import com.tushar.expenses.user.UserId;

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
	@JsonProperty("userId")
	private UserId userId;
	
	@Column(name="user_name")
	private String userName;

	@Column
	private String email;
	
	
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
