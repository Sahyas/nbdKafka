package com.nbd.repository.api.book;

import com.nbd.service.api.book.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    List<BookModel> fetchBooks();

    Optional<BookModel> findBySerialNumber(int id);

    boolean addBook(BookModel book);

    boolean addBooks(List<BookModel> books);

    boolean dropBook(BookModel book);

    boolean updateBook(BookModel book);
}
