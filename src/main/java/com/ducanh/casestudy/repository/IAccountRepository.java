package com.ducanh.casestudy.repository;

import com.ducanh.casestudy.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepository extends JpaRepository<Account, Long> {

}
