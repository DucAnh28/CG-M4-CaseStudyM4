package com.ducanh.casestudy.service.player;

import com.ducanh.casestudy.model.Performance;
import com.ducanh.casestudy.model.Player;
import com.ducanh.casestudy.model.Position;
import com.ducanh.casestudy.model.Status;
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
}
