package com.nbd.service;

import com.nbd.repository.BookRepositoryImpl;
import com.nbd.model.Book;

import java.util.List;
import java.util.UUID;

public class BookServiceImpl {
    private final BookRepositoryImpl bookRepository;

    public BookServiceImpl() {
        this.bookRepository = new BookRepositoryImpl();
    }

    public Object getBookById(UUID id) {
        return null;
    }

    public Book getBook(String serialNumber) {
        return bookRepository.findBySerialNumber(serialNumber);
    }

    public List<Book> findAllBooks() {
        return null;
    }

    public void registerBook(String title, String author, String serialNumber, String genre) {
        bookRepository.add(new Book(title, author, serialNumber, genre));
    }

    public void unregisterBook(Book book) {

    }
}
