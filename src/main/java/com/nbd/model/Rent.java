package com.nbd.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.Date;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
public class Rent extends AbstractEntity {
    @JsonProperty("beginTime")
    @BsonProperty("beginTime")
    private Date beginTime;
    @JsonProperty("endTime")
    @BsonProperty("endTime")
    private Date endTime;
    @JsonProperty("book")
    @BsonProperty("book")
    private Book book;
    @JsonProperty("client")
    @BsonProperty(value = "client", useDiscriminator = true)
    private Client client;
    @JsonCreator
    @BsonCreator
    public Rent(@BsonProperty("_id") @JsonProperty("id") UUID id,
                @BsonProperty("beginTime") @JsonProperty("beginTime") Date beginTime,
                @BsonProperty("endTime") @JsonProperty("endTime") Date endTime,
                @BsonProperty("book") @JsonProperty("book") Book book,
                @BsonProperty("client") @JsonProperty("client") Client client) {
        super(id);
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
        this.endTime = new Date();
    }
}
