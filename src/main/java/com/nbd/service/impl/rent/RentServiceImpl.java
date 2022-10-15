package com.nbd.service.impl.rent;

import com.nbd.service.api.book.Book;
import com.nbd.service.api.client.Client;
import com.nbd.service.api.client.ClientType;
import com.nbd.service.api.rent.Rent;
import com.nbd.service.api.rent.RentService;

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
