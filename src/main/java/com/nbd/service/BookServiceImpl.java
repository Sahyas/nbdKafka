package com.nbd.service;

import com.nbd.repository.BookRepositoryImpl;
import com.nbd.model.Book;

import java.util.List;

public class BookServiceImpl {
    private final BookRepositoryImpl bookRepository;

    public BookServiceImpl(BookRepositoryImpl bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book getBookById(int id) {
        return bookRepository.getById(id);
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Book registerBook(String title, String author, String serialNumber, String genre) {
        return bookRepository.add(new Book(title, author, serialNumber, genre));
    }

    public void unregisterBook(Book book) {
        bookRepository.delete(book);
    }
}
