package com.nbd.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Book extends AbstractEntityMgd {

    private String title;

    private String author;

    private String serialNumber;

    private String genre;

    private boolean isRented;
    public Book(UUID entityId)  {
        super(entityId);
        this.title = title;
        this.author = author;
        this.serialNumber = serialNumber;
        this.genre = genre;
        this.isRented = isRented;
    }

    public Book(String title, String author, String serialNumber, String genre) {
        super(UUID.randomUUID());
        this.title = title;
        this.author = author;
        this.serialNumber = serialNumber;
        this.genre = genre;
        this.isRented = false;
    }
}
