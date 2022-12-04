package com.nbd.model.mapper;

import com.nbd.model.dto.Book;
import com.nbd.model.mongo.BookMgd;
import com.nbd.model.redis.BookRd;

public class BookMapper {
    public BookMgd toBsonDocument(Book book) {
        return BookMgd.builder()
                .genre(book.getGenre())
                .author(book.getAuthor())
                .serialNumber(book.getSerialNumber())
                .title(book.getTitle())
                .id(book.getUniqueId())
                .isRented(book.isRented())
                .build();
    }

    public BookRd toJsonDocument(Book book) {
        return BookRd.builder()
                .genre(book.getGenre())
                .author(book.getAuthor())
                .serialNumber(book.getSerialNumber())
                .title(book.getTitle())
                .id(book.getUniqueId())
                .isRented(book.isRented())
                .build();
    }
}
