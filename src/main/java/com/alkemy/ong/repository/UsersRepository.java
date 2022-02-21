package com.alkemy.ong.repository;


import com.alkemy.ong.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Long> {
}
