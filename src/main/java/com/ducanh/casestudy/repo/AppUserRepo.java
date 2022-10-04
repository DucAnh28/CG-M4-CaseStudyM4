package com.ducanh.casestudy.repo;


import com.ducanh.casestudy.model.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AppUserRepo extends CrudRepository<AppUser, Long> {
    AppUser findByName(String name);
}
