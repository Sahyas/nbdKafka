package com.nbd.repository.redis;

import com.nbd.model.Book;

public class BookRedisRepository extends AbstractRedisRepository<Book> {
    public BookRedisRepository() {
        super(Book.class, "book:");
    }
}
