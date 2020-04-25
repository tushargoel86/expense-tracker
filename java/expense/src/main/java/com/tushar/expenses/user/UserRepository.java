package com.tushar.expenses.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UserId> {

	@Query("from User where userName = :userName")
	Optional<User> existsByUserName(String userName);

	@Query("from User where userId = :userId")
	Optional<User> findByUserId(UserId userId);

}
