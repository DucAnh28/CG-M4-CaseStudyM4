package com.ducanh.casestudy.service.coach;

import com.ducanh.casestudy.model.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ducanh.casestudy.repository.ICoachRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CoachService implements ICoachService {
    List<Coach> coaches = new ArrayList<>();
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

    public void edit(Long id, Coach coach) {
        for (Coach c : coaches) {
            if (id == c.getId()) {
                c = coach;
                break;
            }
        }
    }
}
