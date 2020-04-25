package com.tushar.expenses.user.endpoint;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tushar.expenses.user.Name;
import com.tushar.expenses.user.UserAlreadyRegisteredException;
import com.tushar.expenses.user.UserEmail;
import com.tushar.expenses.user.UserForm;
import com.tushar.expenses.user.UserId;
import com.tushar.expenses.user.UserRequest;
import com.tushar.expenses.user.model.User;
import com.tushar.expenses.user.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	private UserRepository UserRepository;

	@CrossOrigin
	@PostMapping("/user")
	public ResponseEntity<String> createUser(@RequestBody UserRequest request) {
		try {
			User user = new User(new UserForm(new Name(request.getUserName()), new UserEmail(request.getEmail())));
			Optional<User> existsByUserName = UserRepository.existsByUserName(user.getUserName());
			if (existsByUserName.isPresent())
				throw new UserAlreadyRegisteredException();
			User usr = UserRepository.save(user);
			return new ResponseEntity<String>(usr.getUserId().toString(), HttpStatus.CREATED);
		} catch (UserAlreadyRegisteredException e) {
			return new ResponseEntity<String>("user already exists", HttpStatus.PRECONDITION_FAILED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("user details required", HttpStatus.PRECONDITION_FAILED);
		}

	}

	@CrossOrigin
	@GetMapping("/users/{id}")
	public ResponseEntity<UserId> fetchUserId(@PathVariable String id) {
		try {
			UserId uid = new UserId(id);
			Optional<User> ud = UserRepository.findById(uid);
			if (ud.isPresent())
				return new ResponseEntity<UserId>(ud.get().getUserId(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<UserId>(HttpStatus.PRECONDITION_FAILED);
		}
		return new ResponseEntity<UserId>(HttpStatus.NOT_FOUND);
	}
	
	@CrossOrigin
	@GetMapping("/users/v2/{email}")
	public ResponseEntity<UserId> fetchUserIdByEmail(@PathVariable String email) {
		try {
			Optional<User> ud = UserRepository.findByEmail(email);
			if (ud.isPresent())
				return new ResponseEntity<UserId>(ud.get().getUserId(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<UserId>(HttpStatus.PRECONDITION_FAILED);
		}
		return new ResponseEntity<UserId>(HttpStatus.NOT_FOUND);
	}
}
