package com.nbd.model;

import com.nbd.model.Client;
import com.nbd.model.Book;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Rent {

    private final int id;
    private final Date beginTime;
    private Date endTime;
    private final Book book;
    private final Client client;

    public Rent(int id, Book book, Client client) {
        this.id = id;
        this.book = book;
        this.client = client;
        beginTime = new Date();
    }
}
