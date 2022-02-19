package com.alkemy.ong.repository;


import com.alkemy.ong.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
