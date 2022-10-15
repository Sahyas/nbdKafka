package com.nbd.repository.api;

import com.nbd.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    List<Book> fetchBooks();

    Optional<Book> findBySerialNumber(int id);

    boolean addBook(Book book);

    boolean addBooks(List<Book> books);

    boolean dropBook(Book book);

    boolean updateBook(Book book);
}
