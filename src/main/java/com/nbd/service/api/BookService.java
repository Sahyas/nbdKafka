package com.nbd.service.api;

import com.nbd.model.Book;

import java.util.List;

public interface BookService {
    Book getBookById(int serialNumber);

    List<Book> findAllBooks();

    Book registerBook(String title, String author, String serialNumber, String genre);

    boolean unregisterBook(Book book);
}
