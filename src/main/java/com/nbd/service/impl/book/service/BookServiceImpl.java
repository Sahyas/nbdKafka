package com.nbd.service.impl.book.service;

import com.nbd.repository.api.book.BookRepository;
import com.nbd.service.api.book.Book;
import com.nbd.service.api.book.BookService;
import com.nbd.service.impl.book.mapper.BookMapper;

import java.rmi.NoSuchObjectException;
import java.util.List;
import java.util.NoSuchElementException;

public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper = BookMapper.INSTANCE;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book getBookById(int serialNumber) {
        return bookMapper.bookModelToBook(
                bookRepository.findBySerialNumber(serialNumber).get()
        );
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
