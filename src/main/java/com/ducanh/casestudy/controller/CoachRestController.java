package com.ducanh.casestudy.controller;

import com.ducanh.casestudy.model.AppRole;
import com.ducanh.casestudy.model.AppUser;
import com.ducanh.casestudy.model.Coach;
import com.ducanh.casestudy.service.approle.IAppRoleService;
import com.ducanh.casestudy.service.appuser.AppUserService;
import com.ducanh.casestudy.service.coach.ICoachService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/coach")
@CrossOrigin("*")
public class CoachRestController {
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private ICoachService coachService;
    @Autowired
    private IAppRoleService appRoleService;
    @Autowired
    private ServletContext servletContext;
    @Value("${upload_file_avatar}")
    private String upload_file_avatar;

    @GetMapping
    public ResponseEntity<Iterable<Coach>> displayAllCoach() {
        List<Coach> coaches = (List<Coach>) coachService.findAll();
        return new ResponseEntity<>(coaches, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity displayCoachPage(@PageableDefault(value = 2) @ModelAttribute("coach")Coach coach ,Pageable pageable) {
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
    public ResponseEntity<Coach> addCoach(@ModelAttribute("coach") Coach coach, @ModelAttribute("username") String username, @ModelAttribute("password") String password, @ModelAttribute("avaFile") MultipartFile avaFile) {
        String path = servletContext.getRealPath("/");
        System.out.println("path: "+ path);
        if (avaFile != null) {
            String avaFileName = avaFile.getOriginalFilename();
            try {
                FileCopyUtils.copy(avaFile.getBytes(), new File(upload_file_avatar + avaFileName));
                coach.setAvatarURL("/image/" + avaFileName);
            } catch (IOException ex) {
                coach.setAvatarURL("image/Error");
                System.out.println("Loi khi upload File");
                ex.printStackTrace();
            }
        }
        AppUser newUser = new AppUser(username, password);
        Set<AppRole> roles = new HashSet<>();
        roles.add(appRoleService.findById(2L).get());
        newUser.setAppRole(roles);
        appUserService.save(newUser);
        coach.setAppUser(newUser);
        coachService.save(coach);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Coach> editCoach(@PathVariable Long id, Coach coach,@ModelAttribute("avaFile") MultipartFile avaFile) {
        Optional<Coach> coachOptional = coachService.findById(id);
        if (!coachOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        String path = servletContext.getRealPath("/");
        System.out.println("path: "+ path);
        if (avaFile != null) {
            String avaFileName = avaFile.getOriginalFilename();
            try {
                FileCopyUtils.copy(avaFile.getBytes(), new File(upload_file_avatar + avaFileName));
                coach.setAvatarURL("/image/" + avaFileName);
            } catch (IOException ex) {
                coach.setAvatarURL("image/Error");
                System.out.println("Loi khi upload File");
                ex.printStackTrace();
            }
        }
        coach.setId(coachOptional.get().getId());
        return new ResponseEntity<>(coachService.save(coach), HttpStatus.OK);
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


    @GetMapping("/role")
    public ResponseEntity<Iterable<Coach>> findCoachByRole(@RequestParam Optional<String> role,Pageable pageable){
        Page<Coach> coaches=coachService.findAllPage(pageable);
        if (coaches.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        if (role.isPresent()){
            return new ResponseEntity<>(coachService.findCoachByRoleContaining(role.get(),pageable),HttpStatus.OK);
        }
        return new ResponseEntity<>(coaches,HttpStatus.OK);
    }
//        Iterable<Coach> coach1 = coachService.findCoachByNameContaining(role);

    @RequestMapping(value = "/image/{path}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImageAsResponseEntity(@PathVariable String path) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        InputStream in = servletContext.getResourceAsStream(upload_file_avatar+path);
        byte[] media = IOUtils.toByteArray(in);
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/sortAsc")
    public ResponseEntity<Iterable<Coach>> sortCoachBySalaryAsc(){
        Iterable<Coach> coaches=coachService.sortCoachSalaryAsc();
        return new ResponseEntity<>(coaches,HttpStatus.OK);
    }
    @GetMapping("/sortDesc")
    public ResponseEntity<Iterable<Coach>> sortCoachBySalaryDesc(){
        Iterable<Coach> coaches=coachService.sortCoachSalaryDesc();
        return new ResponseEntity<>(coaches,HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<Iterable<Coach>> searchByName(@PageableDefault(value = 2) @RequestParam Optional<String> name,Pageable pageable){
        Page<Coach> coaches = coachService.findAllPage(pageable);
        if (coaches.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        if (name.isPresent()) {
            return new ResponseEntity<>(coachService.findCoachByNameContaining(name.get(), pageable), HttpStatus.OK);
        }
        return new ResponseEntity<>(coaches, HttpStatus.OK);
    }
}
