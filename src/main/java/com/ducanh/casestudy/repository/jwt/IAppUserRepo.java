<<<<<<< HEAD:src/main/java/com/ducanh/casestudy/repo/AppUserRepo.java
//package com.ducanh.casestudy.repo;
//
//
//import com.ducanh.casestudy.model.AppUser;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Repository;
//@Repository
//public interface AppUserRepo extends CrudRepository<AppUser, Long> {
//    AppUser findByName(String name);
//}
=======
package com.ducanh.casestudy.repository.jwt;


import com.ducanh.casestudy.model.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IAppUserRepo extends CrudRepository<AppUser, Long> {
    AppUser findByName(String name);
}
>>>>>>> master:src/main/java/com/ducanh/casestudy/repository/jwt/IAppUserRepo.java
