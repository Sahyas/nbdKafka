package com.nbd.repository.impl;

import com.nbd.model.Book;
import com.nbd.repository.api.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookRepositoryImpl implements BookRepository {
    private final List<Book> books;

    public BookRepositoryImpl() {
        this.books = new ArrayList<>();
    }

    @Override
    public List<Book> fetchBooks() {
        return books;
    }

    @Override
    public Optional<Book> findBySerialNumber(int id) {
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst();
    }

    @Override
    public boolean addBook(Book book) {
        return books.add(book);
    }

    @Override
    public boolean addBooks(List<Book> books) {
        return books.addAll(books);
    }

    @Override
    public boolean dropBook(Book book) {
        return books.remove(book);
    }

    @Override
    public boolean updateBook(Book book) {
        Optional<Book> foundBook = findBySerialNumber(book.getId());
        if (!foundBook.isPresent()) {
            return false;
        }
        dropBook(foundBook.get());
        return addBook(book);
    }
}
