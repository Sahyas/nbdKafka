package com.nbd.repository;

import com.nbd.model.Client;

public class ClientRepositoryImpl extends RedisRepository<Client> {

    public ClientRepositoryImpl() {

    }

    public Client findByPersonalID(String personalId) {
        return null;
    }

    public void clearDatabase() {

    }

    @Override
    public void close() throws Exception {
        System.out.println("closing client repository");
    }
}
