package com.ducanh.casestudy.service.coach;

import com.ducanh.casestudy.model.Coach;
import com.ducanh.casestudy.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICoachService extends IGeneralService<Coach> {
    Page<Coach> findAllPage(Pageable pageable);


}
