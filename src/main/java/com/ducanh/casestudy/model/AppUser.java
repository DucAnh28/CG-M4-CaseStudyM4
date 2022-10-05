package com.ducanh.casestudy.model;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<AppRole> appRole;

    public AppUser(Long id, String name, String password, Set<AppRole> appRole) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.appRole = appRole;
    }

    public Set<AppRole> getAppRole() {
        return appRole;
    }

    public void setAppRole(Set<AppRole> appRole) {
        this.appRole = appRole;
    }

    public AppUser() {
    }

    public AppUser(Long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
