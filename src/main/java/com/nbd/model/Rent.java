package com.nbd.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@ToString
public class Rent extends AbstractEntityMgd {
    private Date beginTime;

    private Date endTime;

    private Book book;

    private Client client;

    public Rent(UUID entityId) {
        super(entityId);
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.book = book;
        this.client = client;
    }

    public Rent(Client client, Book book) {
        super(UUID.randomUUID());
        this.client = client;
        this.book = book;
        this.beginTime = new Date();
    }
}
