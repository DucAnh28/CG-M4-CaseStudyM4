package com.ducanh.casestudy.service.appuser;

import com.ducanh.casestudy.model.AppUser;
import com.ducanh.casestudy.model.dto.ICountRole;
import com.ducanh.casestudy.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetails;

public interface IAppUserService extends IGeneralService<AppUser> {
    public UserDetails loadUserByUsername(String username);

    public AppUser findUserByName(String username);

    public Iterable<ICountRole> getRoleNumber();
}
