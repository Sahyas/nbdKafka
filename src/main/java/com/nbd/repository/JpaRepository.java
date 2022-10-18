package com.nbd.repository;

import jakarta.persistence.Entity;

import java.util.List;

public interface JpaRepository<T> {

    T add(T entity);
    void delete(T entity);
    T getById(int id);
    T update(T entity);
    public List<T> findAll();
}
