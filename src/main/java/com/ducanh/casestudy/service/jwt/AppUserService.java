<<<<<<< HEAD:src/main/java/com/ducanh/casestudy/service/appuser/AppUserService.java
//package com.ducanh.casestudy.service.appuser;
//
//
//import com.ducanh.casestudy.model.AppUser;
//import com.ducanh.casestudy.repo.AppUserRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class AppUserService implements IAppUserService, UserDetailsService {
//    @Autowired
//    private AppUserRepo appUserRepo;
//
//    @Override
//    public Iterable<AppUser> findAll() {
//        return appUserRepo.findAll();
//    }
//
//    @Override
//    public Optional<AppUser> findById(Long id) {
//        return appUserRepo.findById(id);
//    }
//
//    @Override
//    public AppUser save(AppUser appUser) {
//        return appUserRepo.save(appUser);
//    }
//
//    @Override
//    public void remove(Long id) {
//        appUserRepo.deleteById(id);
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        AppUser appUser = appUserRepo.findByName(username);
//
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        grantedAuthorities.add(appUser.getAppRole());
//
//        UserDetails userDetails = new User(
//                appUser.getName(),
//                appUser.getPassword(),
//                grantedAuthorities
//        );
//
//        return userDetails;
//    }
//}
=======
package com.ducanh.casestudy.service.jwt;


import com.ducanh.casestudy.model.AppUser;
import com.ducanh.casestudy.repository.jwt.IAppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserService implements IAppUserService, UserDetailsService {
    @Autowired
    private IAppUserRepo appUserRepo;

    @Override
    public Iterable<AppUser> findAll() {
        return appUserRepo.findAll();
    }

    @Override
    public Optional<AppUser> findById(Long id) {
        return appUserRepo.findById(id);
    }

    @Override
    public AppUser save(AppUser appUser) {
        return appUserRepo.save(appUser);
    }

    @Override
    public void remove(Long id) {
        appUserRepo.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepo.findByName(username);
        return new User(appUser.getName(), appUser.getPassword(), appUser.getAppRole());
    }

    @Override
    public AppUser findUserByName(String username) {
        return appUserRepo.findByName(username);
    }
}
>>>>>>> master:src/main/java/com/ducanh/casestudy/service/jwt/AppUserService.java
