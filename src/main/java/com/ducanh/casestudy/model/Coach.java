package com.ducanh.casestudy.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String country;
    private String achievement;
    private double salary;
    private String role;

    @Transient
    private MultipartFile avaFile;
    private String avatarURL;

    public Coach(String name, String country, String achievement, double salary, String role, MultipartFile avaFile, String avatarURL) {
        this.name = name;
        this.country = country;
        this.achievement = achievement;
        this.salary = salary;
        this.role = role;
        this.avaFile = avaFile;
        this.avatarURL = avatarURL;
    }

    public Coach() {
    }

    public MultipartFile getAvaFile() {
        return avaFile;
    }

    public void setAvaFile(MultipartFile avaFile) {
        this.avaFile = avaFile;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
