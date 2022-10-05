package com.ducanh.casestudy.repository.player;

import com.ducanh.casestudy.model.Performance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPerformanceRepository extends CrudRepository<Performance, Long> {
}
