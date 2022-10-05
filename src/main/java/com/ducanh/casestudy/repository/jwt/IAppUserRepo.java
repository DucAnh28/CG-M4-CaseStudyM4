<<<<<<<< HEAD:src/main/java/com/ducanh/casestudy/repository/AppUserRepo.java
package com.ducanh.casestudy.repository;
========
package com.ducanh.casestudy.repository.jwt;
>>>>>>>> master:src/main/java/com/ducanh/casestudy/repository/jwt/IAppUserRepo.java


import com.ducanh.casestudy.model.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IAppUserRepo extends CrudRepository<AppUser, Long> {
    AppUser findByName(String name);
}
