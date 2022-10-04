package com.ducanh.casestudy.controller;

import com.ducanh.casestudy.model.Coach;
import com.ducanh.casestudy.service.coach.ICoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/coach")
public class CoachController {
    @Autowired
    private ICoachService coachService;

    @GetMapping
    public ResponseEntity<Iterable<Coach>> displayAllCoach() {
        List<Coach> coaches = (List<Coach>) coachService.findAll();
        return new ResponseEntity<>(coaches, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coach> findCoachById(@PathVariable Long id) {
    Optional<Coach> coachOptional = coachService.findById(id);
    if (!coachOptional.isPresent()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(coachOptional.get(), HttpStatus.OK);
}

    @PostMapping
    public ResponseEntity<Coach> addCoach(@RequestBody Coach coach) {
        return new ResponseEntity<>(coachService.save(coach), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Coach> deleteCoach(@PathVariable Long id) {
        Optional<Coach> coachDelete = coachService.findById(id);
        if (!coachDelete.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        coachService.remove(id);
        return new ResponseEntity<>(coachDelete.get(), HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Coach> editCoach(@PathVariable Long id, @RequestBody Coach coach) {
        Optional<Coach> coachOptional = coachService.findById(id);
        if (!coachOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        coach.setId(coachOptional.get().getId());
        return new ResponseEntity<>(coachService.save(coach), HttpStatus.OK);
    }

}
