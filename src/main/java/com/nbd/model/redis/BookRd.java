package com.nbd.model.redis;

import org.bson.codecs.pojo.annotations.BsonCreator;

import java.util.UUID;

import javax.json.bind.annotation.JsonbProperty;

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
public class BookRd extends AbstractEntityRd {
    @JsonbProperty("title")
    private String title;
    @JsonbProperty("author")
    private String author;
    @JsonbProperty("serialNumber")
    private String serialNumber;
    @JsonbProperty("genre")
    private String genre;
    @JsonbProperty("rented")
    private boolean isRented;

    @BsonCreator
    public BookRd(@JsonbProperty("_id") UUID entityId,
                   @JsonbProperty("title") String title,
                   @JsonbProperty("author") String author,
                   @JsonbProperty("serialNumber") String serialNumber,
                   @JsonbProperty("genre") String genre,
                   @JsonbProperty("rented") boolean isRented) {
        super(entityId);
        this.title = title;
        this.author = author;
        this.serialNumber = serialNumber;
        this.genre = genre;
        this.isRented = isRented;
    }

    public BookRd(String title, String author, String serialNumber, String genre) {
        super(UUID.randomUUID());
        this.title = title;
        this.author = author;
        this.serialNumber = serialNumber;
        this.genre = genre;
        this.isRented = false;
    }
}
