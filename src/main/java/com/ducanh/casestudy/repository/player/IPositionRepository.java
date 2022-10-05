package com.ducanh.casestudy.repository.player;

import com.ducanh.casestudy.model.Position;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPositionRepository extends CrudRepository<Position, Long> {
}
