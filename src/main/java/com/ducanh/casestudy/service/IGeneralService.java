package com.ducanh.casestudy.service;

import com.ducanh.casestudy.model.Coach;

import java.util.Optional;


public interface IGeneralService<T>  {
    Iterable<T> findAll();

    Optional<T> findById(Long id);

    T save(T t);

    void remove(Long id);

    void edit(Long id, Coach coach);
}
