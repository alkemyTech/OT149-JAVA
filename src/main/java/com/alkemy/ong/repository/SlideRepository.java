package com.alkemy.ong.repository;

import com.alkemy.ong.model.Slide;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SlideRepository extends JpaRepository<Slide, Long> {
  
	@Query("SELECT MAX(s.order) FROM Slide s WHERE s.organization.id = :idOrganization")
	Integer findMaxOrder(@Param("idOrganization") Long idOrganization);
	
	@Query("SELECT s FROM Slide s WHERE s.organization.id = :idOrganization")
	List<Slide> findByOrg(@Param("idOrganization") Long idOrganization);
}
