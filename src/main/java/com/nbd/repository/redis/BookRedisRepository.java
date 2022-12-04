package com.nbd.repository.redis;

import com.nbd.model.redis.BookRd;

public class BookRedisRepository extends AbstractRedisRepository<BookRd> {
    public BookRedisRepository() {
        super(BookRd.class, "book:");
    }
}
