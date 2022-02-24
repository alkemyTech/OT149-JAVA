package com.alkemy.ong.repository;

import com.alkemy.ong.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivitiesRepository extends JpaRepository<Activity, Long> {
}
