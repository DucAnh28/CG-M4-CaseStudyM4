package com.ducanh.casestudy.repository.coach;

import com.ducanh.casestudy.model.Coach;
import org.springframework.data.domain.Sort;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICoachRepository extends PagingAndSortingRepository<Coach, Long> {
    Iterable<Coach> findCoachByRole(String role);
//    Iterable<Coach> findCoachBySalary( Sort sort);
    @Query("SELECT name,salary FROM coach ORDER BY coach.salary ASC ;")
    Iterable<Coach> findAllCoachSalaryAsc();
//    @Query("SELECT name,salary FROM coach ORDER BY coach.salary DESC ;")
//    Iterable<Coach> findAllCoachSalaryDesc();
}
