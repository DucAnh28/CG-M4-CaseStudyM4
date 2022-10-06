package com.ducanh.casestudy.service.approle;

import com.ducanh.casestudy.model.AppRole;
import com.ducanh.casestudy.model.dto.ICountRole;
import com.ducanh.casestudy.service.IGeneralService;

public interface IAppRoleService extends IGeneralService<AppRole> {
    public AppRole findByName(String name);
}
