package com.nbd.repository;

import com.nbd.model.Book;

public class BookRepositoryImpl extends RedisRepository<Book> {

    public BookRepositoryImpl() {

    }

    public Book findBySerialNumber(String serialNumber) {
        return null;
    }

    public void clearDatabase() {

    }

    @Override
    public void close() throws Exception {
        System.out.println("closing book repository");
    }

    public void updateBook(Book book) {

    }
}
