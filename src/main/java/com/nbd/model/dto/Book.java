package com.nbd.model.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@ToString
@SuperBuilder
public class Book extends AbstractEntity {
    private String title;
    private String author;
    private String serialNumber;
    private String genre;
    private boolean isRented;

    public Book(UUID uniqueId, String title, String author, String serialNumber, String genre, boolean isRented) {
        super(uniqueId);
        this.title = title;
        this.author = author;
        this.serialNumber = serialNumber;
        this.genre = genre;
        this.isRented = isRented;
    }
}
