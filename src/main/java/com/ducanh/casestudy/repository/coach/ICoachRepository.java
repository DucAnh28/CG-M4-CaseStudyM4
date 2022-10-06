package com.ducanh.casestudy.repository.coach;

import com.ducanh.casestudy.model.Coach;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICoachRepository extends PagingAndSortingRepository<Coach,Long> {
    public Iterable<Coach> findCoachByRole(String role);
}
