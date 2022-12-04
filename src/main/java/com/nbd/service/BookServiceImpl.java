package com.nbd.service;

import com.nbd.repository.mongo.BookMongoRepository;
import com.nbd.model.mongo.BookMgd;

import java.util.List;
import java.util.UUID;

public class BookServiceImpl {
    private final BookMongoRepository bookRepository;

    public BookServiceImpl() {
        this.bookRepository = new BookMongoRepository();
    }

    public Object getBookById(UUID id) {
        return bookRepository.getById(id);
    }

    public BookMgd getBook(String serialNumber) {
        return bookRepository.findBySerialNumber(serialNumber);
    }

    public List<BookMgd> findAllBooks() {
        return bookRepository.findAll();
    }

    public void registerBook(String title, String author, String serialNumber, String genre) {
        bookRepository.add(new BookMgd(title, author, serialNumber, genre));
    }

    public void unregisterBook(BookMgd book) {
        bookRepository.delete(book.getId());
    }
}
