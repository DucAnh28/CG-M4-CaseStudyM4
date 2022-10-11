package com.ducanh.casestudy.service.player;

import com.ducanh.casestudy.model.*;
import com.ducanh.casestudy.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IPlayerService extends IGeneralService<Player> {

    Iterable<Position> findAllPosition();

    Iterable<Performance> findAllPerformance();

    Iterable<Status> findAllStatus();

    Iterable<Player> findAllByName(String name);

    Page<Player> findPage(Pageable pageable);
    Iterable<Player> sortPlayerSalaryAsc();
    Iterable<Player> sortPlayerSalaryDesc();

    Page<Player> findAllPage(Pageable pageable);

//    Object findPlayerByNameContaining(String s, Pageable pageable);
Page<Player> findPlayerByNameContaining(String name, Pageable pageable);

}

