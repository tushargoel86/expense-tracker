package com.tushar.expenses.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tushar.expenses.user.UserId;
import com.tushar.expenses.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, UserId> {

	@Query("from User where userName = :userName")
	Optional<User> existsByUserName(String userName);

	@Query("from User where userId = :userId")
	Optional<User> findByUserId(UserId userId);

	@Query("from User where email = :email")
	Optional<User> findByEmail(String email);

}
