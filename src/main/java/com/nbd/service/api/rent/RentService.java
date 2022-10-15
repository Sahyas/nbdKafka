package com.nbd.service.api.rent;

import com.nbd.service.api.book.Book;
import com.nbd.service.api.client.Client;
import com.nbd.service.api.client.ClientType;

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
