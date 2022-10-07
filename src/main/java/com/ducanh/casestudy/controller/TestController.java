package com.ducanh.casestudy.controller;

import com.ducanh.casestudy.model.AppRole;
import com.ducanh.casestudy.model.AppUser;
import com.ducanh.casestudy.model.Coach;
import com.ducanh.casestudy.service.approle.IAppRoleService;
import com.ducanh.casestudy.service.appuser.IAppUserService;
import com.ducanh.casestudy.service.coach.ICoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private ICoachService coachService;
    @Autowired
    private IAppRoleService appRoleService;
    @Autowired
    private IAppUserService appUserService;

    @Value("${upload_file_avatar}")
    private String upload_file_avatar;

    @PostMapping
    public ResponseEntity<Coach> addCoach(@ModelAttribute("coach") Coach coach,@ModelAttribute("username") String username ,@ModelAttribute("password") String password, @ModelAttribute("avaFile") MultipartFile avaFile) {
        if (avaFile != null){
            String avaFileName = avaFile.getOriginalFilename();
            try {
                FileCopyUtils.copy(avaFile.getBytes(), new File(upload_file_avatar + avaFileName));
                coach.setAvatarURL("image/Avatar" + avaFileName);
            } catch (IOException ex) {
                coach.setAvatarURL("image/Error");
                System.out.println("Loi khi upload File");
                ex.printStackTrace();
            }
        }
        AppUser newUser = new AppUser(username,password);
        Set<AppRole> roles = new HashSet<>();
        roles.add(appRoleService.findById(2L).get());
        newUser.setAppRole(roles);
        appUserService.save(newUser);
        coach.setAppUser(newUser);
        coachService.save(coach);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
