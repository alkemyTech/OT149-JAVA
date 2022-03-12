package com.alkemy.ong.repository;

import com.alkemy.ong.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	Optional<User> findUserByEmail(String email);
	User getUserByEmail(String email);
	Optional<User> findByEmail(String email);
}
