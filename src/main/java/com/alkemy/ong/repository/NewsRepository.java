package com.alkemy.ong.repository;

import com.alkemy.ong.model.New;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<New, Long> {
}
