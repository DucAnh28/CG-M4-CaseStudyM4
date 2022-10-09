package com.ducanh.casestudy.controller;

import com.ducanh.casestudy.model.AppRole;
import com.ducanh.casestudy.model.AppUser;
import com.ducanh.casestudy.model.dto.UserToken;
import com.ducanh.casestudy.service.approle.IAppRoleService;
import com.ducanh.casestudy.service.appuser.IAppUserService;
import com.ducanh.casestudy.service.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin("*")
public class LoginAPI {
    @Autowired
    JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IAppUserService userService;

    @Autowired
    private IAppRoleService appRoleService;

    @PostMapping("/login")
    public ResponseEntity<UserToken> login(@RequestBody AppUser appUser) {
        try {
            // Tạo 1 đối tượng authentication
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(appUser.getName(), appUser.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtService.createToken(authentication);
            AppUser appUser1 = userService.findUserByName(appUser.getName());
            UserToken userToken = new UserToken(appUser1.getId(), appUser1.getPassword(), token, appUser1.getAppRole());
            return new ResponseEntity<>(userToken,HttpStatus.ACCEPTED);
        } catch (Exception e) {
            System.out.println("Loi khi dang nhap");
            return null;
        }
    }

    @PostMapping("/register")
    public ResponseEntity<AppUser> register(@RequestBody AppUser appUser) {
        Set<AppRole> roles = new HashSet<>();
        roles.add(appRoleService.findById(3L).get());
        appUser.setAppRole(roles);
        return new ResponseEntity<>(userService.save(appUser), HttpStatus.OK);
    }
}
