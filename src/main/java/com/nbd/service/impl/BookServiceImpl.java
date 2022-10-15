package com.nbd.service.impl;

import com.nbd.repository.api.BookRepository;
import com.nbd.model.Book;
import com.nbd.service.api.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book getBookById(int serialNumber) {
        return bookRepository.findBySerialNumber(serialNumber).get();
    }

    @Override
    public List<Book> findAllBooks() {
        return null;
    }

    @Override
    public Book registerBook(String title, String author, String serialNumber, String genre) {
        return null;
    }

    @Override
    public boolean unregisterBook(Book book) {
        return false;
    }
}
