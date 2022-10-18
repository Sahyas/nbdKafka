package com.nbd.service;

import com.nbd.model.Book;
import com.nbd.model.Client;
import com.nbd.model.Rent;
import com.nbd.repository.BookRepositoryImpl;
import com.nbd.repository.ClientRepositoryImpl;
import com.nbd.repository.RentRepositoryImpl;

import java.util.List;

public class RentServiceImpl {
    private final RentRepositoryImpl rentRepository;
    private final ClientRepositoryImpl clientRepository;
    private final BookRepositoryImpl bookRepository;

    public RentServiceImpl(RentRepositoryImpl rentRepository, ClientRepositoryImpl clientRepository, BookRepositoryImpl bookRepository) {
        this.rentRepository = rentRepository;
        this.clientRepository = clientRepository;
        this.bookRepository = bookRepository;
    }

    public void rentBook(Client client, Book book) {
        Book foundBook = bookRepository.getById(book.getId());
        if (foundBook != null) {
            throw new RuntimeException("Cannot rent this book");
        }

    }

    public boolean returnBook(Book book) {
        return false;
    }

    public List<Rent> findAllCurrentRents() {
        return rentRepository.findAll();
    }
}
