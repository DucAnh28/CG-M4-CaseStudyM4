package com.ducanh.casestudy.repository.coach;

import com.ducanh.casestudy.model.Coach;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICoachRepository extends PagingAndSortingRepository<Coach,Long> {
}
