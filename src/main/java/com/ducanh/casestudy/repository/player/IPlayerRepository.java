package com.ducanh.casestudy.repository.player;

import com.ducanh.casestudy.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPlayerRepository extends PagingAndSortingRepository<Player, Long> {

//    @Query(value = "select * from player as p order by p.id desc limit 1;", nativeQuery = true)
//    Player findPlayerLast();

    Iterable<Player> findAllByNameContaining(String name);

  @Query(value = "select * from player as p order by p.salary desc limit 1;", nativeQuery = true)
    Player findPlayerMaxSalary();
  @Query(value = "select *from player order by salary asc;", nativeQuery = true)
    Iterable<Player> sortPlayerSalaryAsc();

//    @Query(value = "select sum(p.bonus) from player as p ;", nativeQuery = true)
//    Double totalPlayerBonus();

    @Query (value = "select *from player order by salary desc;", nativeQuery = true)
    Iterable<Player> sortPlayerSalaryDesc();


}
