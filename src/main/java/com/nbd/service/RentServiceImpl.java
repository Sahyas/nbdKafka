package com.nbd.service;

import com.nbd.model.Book;
import com.nbd.model.Client;
import com.nbd.model.Rent;
import com.nbd.repository.BookRepositoryImpl;
import com.nbd.repository.ClientRepositoryImpl;
import com.nbd.repository.RentRepositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RentServiceImpl {
    private final RentRepositoryImpl rentRepository;
    private final ClientRepositoryImpl clientRepository;
    private final BookRepositoryImpl bookRepository;

    public RentServiceImpl(RentRepositoryImpl rentRepository, ClientRepositoryImpl clientRepository, BookRepositoryImpl bookRepository) {
        this.rentRepository = rentRepository;
        this.clientRepository = clientRepository;
        this.bookRepository = bookRepository;
    }

    public boolean rentBook(Client client, List<Book> books) {
        List<Rent> currentRents = findAllCurrentRents();
        List<Book> allBooks = new ArrayList<>();
        currentRents.forEach(rent -> allBooks.addAll(rent.getBooks()));
        if(allBooks.hashCode() == books.hashCode()) {
            return false;
        }

        rentRepository.add(new Rent(books, client));
        return true;
    }

    public boolean returnBook(Book book) {
        return false;
    }

    public List<Rent> findAllCurrentRents() {
        return rentRepository.findAll();
    }
}
