package com.ducanh.casestudy.repository.player;

import com.ducanh.casestudy.model.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStatusRepository extends CrudRepository<Status, Long> {
}
