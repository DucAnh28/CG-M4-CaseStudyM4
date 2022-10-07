package com.ducanh.casestudy.service.coach;

import com.ducanh.casestudy.model.Coach;
import com.ducanh.casestudy.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jdbc.repository.query.Query;

import java.util.List;

public interface ICoachService extends IGeneralService<Coach> {
    Page<Coach> findAllPage(Pageable pageable);

     Iterable<Coach> findCoachByRole(String role);

    Iterable<Coach> findAllCoachSalaryAsc();
//    Iterable<Coach> findAllCoachSalaryDesc();
}
