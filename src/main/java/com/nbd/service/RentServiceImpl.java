package com.nbd.service;

import com.nbd.model.Book;
import com.nbd.model.Client;
import com.nbd.model.Rent;
import com.nbd.repository.BookRepositoryImpl;
import com.nbd.repository.ClientRepositoryImpl;
import com.nbd.repository.RentRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class RentServiceImpl {
    private final RentRepositoryImpl rentRepository;
    private final ClientRepositoryImpl clientRepository;
    private final BookRepositoryImpl bookRepository;

    public RentServiceImpl() {
        this.rentRepository = new RentRepositoryImpl();
        this.clientRepository = new ClientRepositoryImpl();
        this.bookRepository = new BookRepositoryImpl();
    }

    public void rentBook(String clientPersonalId, String bookSerialNumber) {
        Client client = clientRepository.findByPersonalID(clientPersonalId);
        Book book = bookRepository.findBySerialNumber(bookSerialNumber);
        Rent rent = new Rent(client, book);
        if (!book.isRented()) {
            book.setRented(true);
            bookRepository.updateBook(book);
            rentRepository.add(rent);
        }
    }

    public boolean returnBook(Book book) {
        if(book.isRented()) {
            Book foundBook = bookRepository.findBySerialNumber(book.getSerialNumber());
            Rent rent = rentRepository.findByBook(book);
            rentRepository.delete(rent.getId());
            foundBook.setRented(false);
            bookRepository.updateBook(book);
            return true;
        } else {
            System.out.println("Ta książka nie jest wypozyczona");
            return false;
        }
    }

    public List<Rent> findAllCurrentRents() {
        return rentRepository.findAll();
    }

    public Rent getRentByBook(String serialnumber) {
        return rentRepository.findByBook(bookRepository.findBySerialNumber(serialnumber));
    }

    public Rent getRentByClient(String personalID) {
        return rentRepository.findByClient(clientRepository.findByPersonalID(personalID));
    }
}
