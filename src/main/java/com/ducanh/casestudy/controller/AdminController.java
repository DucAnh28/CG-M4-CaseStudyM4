package com.ducanh.casestudy.controller;

import com.ducanh.casestudy.model.AppUser;
import com.ducanh.casestudy.model.dto.ICountRole;
import com.ducanh.casestudy.service.appuser.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private IAppUserService appUserService;
    @GetMapping()
    public String adminHome(){
        return "/admin/home";
    }
    @GetMapping("/user")
    public ResponseEntity<Iterable<AppUser>> getAllUser(){
        List<AppUser> appUsers = (List<AppUser>) appUserService.findAll();
        return new ResponseEntity<>(appUsers, HttpStatus.OK);
    }
    @GetMapping("/countRole")
    public ResponseEntity<Iterable<ICountRole>> getCountRole(){
        Iterable<ICountRole> countRoles = appUserService.getRoleNumber();
        return new ResponseEntity<>(countRoles,HttpStatus.OK);
    }

    @GetMapping("/end")
    public String end(){
        return "end";
    }
}
