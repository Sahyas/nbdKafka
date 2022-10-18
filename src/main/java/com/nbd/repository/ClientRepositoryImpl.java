package com.nbd.repository;

import com.nbd.model.Client;
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

    @Override
    public Client getById(int id) {
        return em.find(Client.class, id);
    }
}
