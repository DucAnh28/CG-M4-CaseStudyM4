package com.ducanh.casestudy.service.approle;

import com.ducanh.casestudy.model.AppRole;
import com.ducanh.casestudy.repository.jwt.IAppRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class AppRoleService implements IAppRoleService{
    @Autowired
    private IAppRoleRepo roleRepo;

    @Override
    public Iterable<AppRole> findAll() {
        return roleRepo.findAll();
    }

    @Override
    public Optional<AppRole> findById(Long id) {
        return roleRepo.findById(id);
    }

    @Override
    public AppRole save(AppRole appRole) {
        return roleRepo.save(appRole);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public AppRole findByName(String name) {
        return roleRepo.findByName(name);
    }
}
