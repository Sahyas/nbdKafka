package com.nbd.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
    private int id;
    private String title;
    private String author;
    private String serialNumber;
    private String genre;
    boolean IsArchive = false;

    public Book() {
    }

    public Book(String title, String author, String serialNumber, String genre, int id) {
        this.title = title;
        this.author = author;
        this.serialNumber = serialNumber;
        this.genre = genre;
        this.id = id;
    }
}
