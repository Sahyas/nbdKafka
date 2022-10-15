package com.nbd.service.api.rent;

import com.nbd.service.api.client.Client;
import com.nbd.service.api.book.Book;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

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

    public int getId() {
        return id;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public Book getBook() {
        return book;
    }

    public Client getClient() {
        return client;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
