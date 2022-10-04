package com.ducanh.casestudy.repository;

import com.ducanh.casestudy.model.Coach;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICoachRepository extends PagingAndSortingRepository<Coach,Long> {
}
