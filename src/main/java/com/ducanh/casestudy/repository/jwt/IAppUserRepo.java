package com.ducanh.casestudy.repository.jwt;


import com.ducanh.casestudy.model.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IAppUserRepo extends CrudRepository<AppUser, Long> {
    AppUser findByName(String name);
}
