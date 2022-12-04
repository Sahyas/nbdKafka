package com.nbd.repository;

import java.util.List;
import java.util.UUID;

public interface RepositoryInterface<T> {
    T add(T entity);
    T getById(UUID id);
    void delete(UUID id);
    T update(T entity);
    long size();
    List<T> findAll();
    T findByPersonalID(String personalId);
}
