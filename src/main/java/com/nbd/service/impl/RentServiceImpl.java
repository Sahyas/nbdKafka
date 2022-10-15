package com.nbd.service.impl;

import com.nbd.model.Book;
import com.nbd.model.Client;
import com.nbd.model.ClientType;
import com.nbd.model.Rent;
import com.nbd.service.api.RentService;

import java.util.List;

public class RentServiceImpl implements RentService {
    @Override
    public Rent rentBook(Client client, Book book) {
        return null;
    }

    @Override
    public boolean returnBook(Book book) {
        return false;
    }

    @Override
    public String findAllCurrentRents() {
        return null;
    }

    @Override
    public void changeClientType(Client client, ClientType clientType) {

    }

    @Override
    public List<Rent> getAllClientRents(Client client) {
        return null;
    }

    @Override
    public List<Rent> getAllBookRents(Book book) {
        return null;
    }

    @Override
    public String findAllArchiveRents() {
        return null;
    }

    @Override
    public boolean isAfterEndTime(Client client) {
        return false;
    }
}
