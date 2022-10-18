package com.nbd.repository;

import com.nbd.model.Client;
import com.nbd.model.Rent;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ClientRepositoryImpl extends JpaRepositoryImpl<Client> {

    public ClientRepositoryImpl(EntityManager em) {
        super(em);
    }

    @Override
    public List<Client> findAll() {
        return em.createQuery("Select client from Client client", Client.class).getResultList();
    }
}
