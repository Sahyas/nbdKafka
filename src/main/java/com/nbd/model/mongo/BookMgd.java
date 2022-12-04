package com.nbd.model.mongo;

import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.UUID;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@SuperBuilder
public class BookMgd extends AbstractEntityMgd {
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
    public BookMgd(@BsonProperty("_id") UUID entityId,
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

    public BookMgd(String title, String author, String serialNumber, String genre) {
        super(UUID.randomUUID());
        this.title = title;
        this.author = author;
        this.serialNumber = serialNumber;
        this.genre = genre;
        this.isRented = false;
    }
}
