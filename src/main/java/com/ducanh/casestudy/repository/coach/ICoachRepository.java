package com.ducanh.casestudy.repository.coach;

import com.ducanh.casestudy.model.Coach;
import com.ducanh.casestudy.model.dto.ICountRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICoachRepository extends PagingAndSortingRepository<Coach, Long> {
    Page<Coach> findCoachByRoleContaining(String role, Pageable pageable);
    Page<Coach> findCoachByNameContaining(String name,Pageable pageable);
    @Query(nativeQuery = true, value = "select * from coach order by salary ASC ;")
    Iterable<Coach> sortCoachSalaryAsc();

    @Query(nativeQuery = true, value = "select * from coach order by salary DESC ;")
    Iterable<Coach> sortCoachSalaryDesc();
}
