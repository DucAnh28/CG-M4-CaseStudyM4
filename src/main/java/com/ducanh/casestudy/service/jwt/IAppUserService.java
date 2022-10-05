package com.ducanh.casestudy.service.jwt;

import com.ducanh.casestudy.model.AppUser;
import com.ducanh.casestudy.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetails;

public interface IAppUserService extends IGeneralService<AppUser> {
    public UserDetails loadUserByUsername(String username);

    public AppUser findUserByName(String username);
}
