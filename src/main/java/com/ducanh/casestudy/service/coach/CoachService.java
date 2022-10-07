package com.ducanh.casestudy.service.coach;

import com.ducanh.casestudy.model.Coach;
import com.ducanh.casestudy.model.dto.ICountRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.ducanh.casestudy.repository.coach.ICoachRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CoachService implements ICoachService {

    @Autowired
    private ICoachRepository coachRepository;

    @Override
    public Iterable<Coach> findAll() {
        return coachRepository.findAll();
    }

    @Override
    public Optional<Coach> findById(Long id) {
        return coachRepository.findById(id);
    }

    @Override
    public Coach save(Coach coach) {
        return coachRepository.save(coach);
    }

    @Override
    public void remove(Long id) {
        coachRepository.deleteById(id);
    }

    @Override
    public Page<Coach> findAllPage(Pageable pageable) {
        return coachRepository.findAll(pageable);
    }

    @Override
    public Iterable<Coach> findCoachByRole(String role) {
        return coachRepository.findCoachByRole(role);
    }

    @Override
    public Iterable<Coach> sortCoachSalaryAsc() {
//        Iterable<Coach> coaches = coachRepository.findAll();
        //sap xep;
        return coachRepository.sortCoachSalaryAsc();
    }

    @Override
    public Iterable<Coach> sortCoachSalaryDesc() {
        return coachRepository.sortCoachSalaryDesc();
    }


}
