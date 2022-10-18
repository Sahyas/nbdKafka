package com.nbd.repository;

import com.nbd.model.AbstractEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.apache.commons.lang3.NotImplementedException;

import java.util.List;

public abstract class JpaRepositoryImpl<T extends AbstractEntity> implements JpaRepository<T> {

    protected EntityManager em;

    public JpaRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public T add(T entity) {
        EntityTransaction entityTransaction = em.getTransaction();
        entityTransaction.begin();
        em.persist(entity);
        entityTransaction.commit();
        return entity;
    }

    @Override
    public void delete(T entity) {
        EntityTransaction entityTransaction = em.getTransaction();
        entityTransaction.begin();
        em.remove(entity);
        entityTransaction.commit();
    }

    @Override
    public T getById(int id) {
        throw new NotImplementedException();
    }

    @Override
    public T update(T entity) {
        EntityTransaction entityTransaction = em.getTransaction();
        entityTransaction.begin();
        em.merge(entity);
        entityTransaction.commit();
        return entity;
    }

    @Override
    public List<T> findAll() {
        throw new NotImplementedException();
    }
}
