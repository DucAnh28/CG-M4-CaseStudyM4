package com.ducanh.casestudy.service.coach;

import com.ducanh.casestudy.model.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.ducanh.casestudy.repository.coach.ICoachRepository;

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



}
