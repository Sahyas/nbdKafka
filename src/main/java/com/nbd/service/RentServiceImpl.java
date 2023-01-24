package com.nbd.service;

import com.nbd.model.Book;
import com.nbd.model.Client;
import com.nbd.model.Rent;
import com.nbd.repository.RentRepository;
import com.nbd.repository.mongo.RentMongoRepository;
import com.nbd.repository.redis.RentRedisRepository;

import java.util.List;
import java.util.UUID;

public class RentServiceImpl {
    private final RentRepository rentRepository;
    private final ClientServiceImpl clientService;
    private final BookServiceImpl bookService;

    public RentServiceImpl(ClientServiceImpl clientService, BookServiceImpl bookService) {
        this.rentRepository = new RentRepository(new RentMongoRepository(), new RentRedisRepository());
        this.clientService = clientService;
        this.bookService = bookService;
    }

    public void rentBook(UUID clientId, UUID bookId) {
        Client client = clientService.getClientById(clientId);
        Book book = bookService.getBookById(bookId);
        Rent rent = new Rent(client, book);
        if (!book.isRented()) {
            book.setRented(true);
            bookService.updateBook(book);
            rentRepository.add(rent);
        }
    }

    public boolean returnBook(Book book) {
        if(book.isRented()) {
            Book foundBook = bookService.getBookById(book.getId());
            Rent rent = rentRepository.findByBook(book);
            rentRepository.delete(rent.getId());
            foundBook.setRented(false);
            bookService.updateBook(book);
            return true;
        } else {
            System.out.println("Ta książka nie jest wypozyczona");
            return false;
        }
    }

    public List<Rent> findAllCurrentRents() {
        return rentRepository.findAll();
    }

    public Rent getRentByBook(Book book) {
        return rentRepository.findByBook(book);
    }

    public Rent getRentByClient(Client client) {
        return rentRepository.findByClient(client);
    }
}
