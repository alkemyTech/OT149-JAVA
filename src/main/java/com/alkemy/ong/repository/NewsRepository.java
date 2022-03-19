package com.alkemy.ong.repository;

import com.alkemy.ong.model.New;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends PagingAndSortingRepository<New, Long> {
}
