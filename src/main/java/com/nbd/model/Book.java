package com.nbd.model;

import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.UUID;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@SuperBuilder
public class Book extends AbstractEntity {
    @BsonProperty("title")
    private String title;
    @BsonProperty("author")
    private String author;
    @BsonProperty("serialNumber")
    private String serialNumber;
    @BsonProperty("genre")
    private String genre;
    @BsonProperty("rented")
    private boolean isRented;
    @BsonCreator
    public Book(@BsonProperty("_id") UUID entityId,
                @BsonProperty("title") String title,
                @BsonProperty("author") String author,
                @BsonProperty("serialNumber") String serialNumber,
                @BsonProperty("genre") String genre,
                @BsonProperty("rented") boolean isRented) {
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
