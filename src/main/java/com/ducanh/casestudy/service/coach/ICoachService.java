package com.ducanh.casestudy.service.coach;

import com.ducanh.casestudy.model.AppUser;
import com.ducanh.casestudy.model.Coach;
import com.ducanh.casestudy.model.dto.ICountRole;
import com.ducanh.casestudy.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

import java.util.Optional;

public interface ICoachService extends IGeneralService<Coach> {
    Page<Coach> findAllPage(Pageable pageable);

    public Iterable<Coach> findCoachByRole(String role);


}
