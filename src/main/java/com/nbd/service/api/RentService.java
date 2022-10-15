package com.nbd.service.api;

import com.nbd.model.Book;
import com.nbd.model.Client;
import com.nbd.model.ClientType;
import com.nbd.model.Rent;

import java.util.List;

public interface RentService {
    Rent rentBook(Client client, Book book);

    boolean returnBook(Book book);

    String findAllCurrentRents();

    void changeClientType(Client client, ClientType clientType);

    List<Rent> getAllClientRents(Client client);

    List<Rent> getAllBookRents(Book book);

    String findAllArchiveRents();

    boolean isAfterEndTime(Client client);
}
