package com.nbd.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Book extends AbstractEntityMgd {
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
