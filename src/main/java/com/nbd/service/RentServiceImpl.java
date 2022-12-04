package com.nbd.service;

import com.nbd.model.mongo.BookMgd;
import com.nbd.model.mongo.ClientMgd;
import com.nbd.model.mongo.RentMgd;
import com.nbd.repository.mongo.BookMongoRepository;
import com.nbd.repository.mongo.ClientMongoRepository;
import com.nbd.repository.mongo.RentMongoRepository;

import java.util.List;

public class RentServiceImpl {
    private final RentMongoRepository rentRepository;
    private final ClientMongoRepository clientRepository;
    private final BookMongoRepository bookRepository;

    public RentServiceImpl() {
        this.rentRepository = new RentMongoRepository();
        this.clientRepository = new ClientMongoRepository();
        this.bookRepository = new BookMongoRepository();
    }

    public void rentBook(String clientPersonalId, String bookSerialNumber) {
        ClientMgd client = clientRepository.findByPersonalID(clientPersonalId);
        BookMgd book = bookRepository.findBySerialNumber(bookSerialNumber);
        RentMgd rent = new RentMgd(client, book);
        if (!book.isRented()) {
            book.setRented(true);
            bookRepository.updateBook(book);
            rentRepository.add(rent);
        }
    }

    public boolean returnBook(BookMgd book) {
        if(book.isRented()) {
            BookMgd foundBook = bookRepository.findBySerialNumber(book.getSerialNumber());
            RentMgd rent = rentRepository.findByBook(book);
            rentRepository.delete(rent.getId());
            foundBook.setRented(false);
            bookRepository.updateBook(book);
            return true;
        } else {
            System.out.println("Ta książka nie jest wypozyczona");
            return false;
        }
    }

    public List<RentMgd> findAllCurrentRents() {
        return rentRepository.findAll();
    }

    public RentMgd getRentByBook(String serialnumber) {
        return rentRepository.findByBook(bookRepository.findBySerialNumber(serialnumber));
    }

    public RentMgd getRentByClient(String personalID) {
        return rentRepository.findByClient(clientRepository.findByPersonalID(personalID));
    }
}
