package com.nbd.repository;

import com.nbd.model.Rent;
import jakarta.persistence.EntityManager;

import java.util.List;

public class RentRepositoryImpl extends JpaRepositoryImpl<Rent> {

    public RentRepositoryImpl(EntityManager em) {
        super(em);
    }

    @Override
    public List<Rent> findAll() {
        return em.createQuery("Select rent from Rent rent", Rent.class).getResultList();
    }
}
