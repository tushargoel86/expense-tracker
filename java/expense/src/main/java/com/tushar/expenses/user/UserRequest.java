package com.tushar.expenses.user;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserRequest {
	
	@NotBlank
	private String userName;
	
	@NotBlank
	private String email;

}
