package com.ducanh.casestudy.controller;

import com.ducanh.casestudy.model.AppRole;
import com.ducanh.casestudy.model.AppUser;
import com.ducanh.casestudy.model.Coach;
import com.ducanh.casestudy.service.approle.IAppRoleService;
import com.ducanh.casestudy.service.appuser.IAppUserService;
import com.ducanh.casestudy.service.coach.ICoachService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/test")
public class FIleController {
    @Autowired
    private ServletContext servletContext;
    @Autowired
    private ICoachService coachService;
    @Autowired
    private IAppRoleService appRoleService;
    @Autowired
    private IAppUserService appUserService;

    @Value("${upload_file_avatar}")
    private String upload_file_avatar;

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

    @GetMapping("/{id}")
    public ResponseEntity<Coach> findCoachById(@PathVariable Long id) throws IOException {
        Optional<Coach> coachOptional = coachService.findById(id);
        if (!coachOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(coachOptional.get(), HttpStatus.OK);
    }
//        trả về ảnh
    @RequestMapping(value = "/image/{path}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImageAsResponseEntity(@PathVariable String path) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        InputStream in = servletContext.getResourceAsStream(upload_file_avatar+path);
        byte[] media = IOUtils.toByteArray(in);
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
        return responseEntity;
    }

}
