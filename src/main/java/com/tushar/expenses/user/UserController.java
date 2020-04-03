package com.tushar.expenses.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tushar.expenses.exception.UserAlreadyRegistered;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository UserRepository;

	@PostMapping("/user")
	public ResponseEntity<String> createUser(@RequestBody UserRequest request) {
		try {
			User user  = new User(new UserForm(new Name(request.getUserName()), new UserEmail(request.getEmail())));
			Optional<User> existsByUserName = UserRepository.existsByUserName(user.getUserName());
			if (existsByUserName.isPresent()) throw new UserAlreadyRegistered();
			User usr = UserRepository.save(user);
			return new ResponseEntity<String>(usr.getUserId().toString(), HttpStatus.CREATED);	
		} catch (Exception e) {
			return new ResponseEntity<String>("user details required",  HttpStatus.PRECONDITION_FAILED);
		}
		
	}
}
