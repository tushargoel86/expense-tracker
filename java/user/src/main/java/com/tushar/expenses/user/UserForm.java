package com.tushar.expenses.user;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserForm implements Serializable {

	private Name userName;
	private UserEmail email;
	
}
