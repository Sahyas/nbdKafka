package com.nbd.service;

import com.nbd.model.Book;
import com.nbd.repository.BookRepository;
import com.nbd.repository.mongo.BookMongoRepository;
import com.nbd.repository.redis.BookRedisRepository;

import java.util.List;
import java.util.UUID;

public class BookServiceImpl {
    private BookRepository bookRepository;
    public BookServiceImpl() {
        this.bookRepository = new BookRepository(new BookRedisRepository(), new BookMongoRepository());
    }

    public Book getBookById(UUID id) {
        return bookRepository.getById(id).orElse(null);
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Book registerBook(String title, String author, String serialNumber, String genre) {
        return bookRepository.add(new Book(title, author, serialNumber, genre));
    }
    public void unregisterBook(Book book) {
        bookRepository.delete(book.getId());
    }

    public Book updateBook(Book book) {
        return bookRepository.update(book);
    }
}
