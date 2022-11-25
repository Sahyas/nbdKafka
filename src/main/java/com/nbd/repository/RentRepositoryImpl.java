package com.nbd.repository;

import com.nbd.model.Book;
import com.nbd.model.Client;
import com.nbd.model.Rent;

public class RentRepositoryImpl extends RedisRepository<Rent> {
    public RentRepositoryImpl() {
    }

    public void clearDatabase() {

    }

    public Rent findByBook(Book book) {
        return null;
    }

    public Rent findByClient(Client client) {
        return null;
    }

    public void updateRent(Rent rent) {

    }

    @Override
    public void close() throws Exception {
        System.out.println("closing rent repository");
    }
}
