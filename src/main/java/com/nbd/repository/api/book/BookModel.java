package com.nbd.repository.api.book;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class BookModel implements Serializable {
    @Id
    private int serialNumber;
    private String title;
    private String author;
    private String genre;
    boolean isArchive;

    public int getSerialNumber() {
        return serialNumber;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public boolean isArchive() {
        return isArchive;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setArchive(boolean archive) {
        isArchive = archive;
    }
}
