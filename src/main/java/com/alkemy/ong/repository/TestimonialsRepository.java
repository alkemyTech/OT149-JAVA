package com.alkemy.ong.repository;

import com.alkemy.ong.model.Testimonials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestimonialsRepository extends JpaRepository<Testimonials, Integer> {
}
