package com.ducanh.casestudy.controller;

import com.ducanh.casestudy.model.AppRole;
import com.ducanh.casestudy.model.Coach;
import com.ducanh.casestudy.service.approle.IAppRoleService;
import com.ducanh.casestudy.service.coach.ICoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/coach")
public class CoachRestController {
    @Autowired
    private ICoachService coachService;
    @Autowired
    private IAppRoleService appRoleService;

    @Value("${upload_file_avatar}")
    private String upload_file_avatar;

    @GetMapping
    public ResponseEntity<Iterable<Coach>> displayAllCoach() {
        List<Coach> coaches = (List<Coach>) coachService.findAll();
        return new ResponseEntity<>(coaches, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity displayCoachPage(@PageableDefault(value = 2) Pageable pageable) {
        Page<Coach> coaches = coachService.findAllPage(pageable);
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
    public ResponseEntity<Coach> addCoach(@ModelAttribute("coach") Coach coach, @ModelAttribute("avaFile") MultipartFile avaFile) {
        String avaFileName = avaFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(avaFile.getBytes(), new File(upload_file_avatar + avaFileName));
            coach.setAvatarURL("image/Avatar" + avaFileName);
        } catch (IOException ex) {
            coach.setAvatarURL("image/Error");
            System.out.println("Loi khi upload File");
            ex.printStackTrace();
        }
        Set<AppRole> roles = new HashSet<>();
        roles.add(appRoleService.findById(2L).get());
        coach.getAppUser().setAppRole(roles);
        coachService.save(coach);
        return new ResponseEntity<>(HttpStatus.CREATED);
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

    @GetMapping("/role")
    public ResponseEntity<Iterable<Coach>> findCoachByRole(@RequestParam String role) {
        Iterable<Coach> coach1 = coachService.findCoachByRole(role);
        return new ResponseEntity<>(coach1, HttpStatus.OK);
    }
}
