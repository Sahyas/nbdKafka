package com.nbd.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonProperty;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@ToString
public class Rent extends AbstractEntityMgd {
    @BsonProperty("beginTime")
    private Date beginTime;
    @BsonProperty("endTime")
    private Date endTime;
    @BsonProperty("book")
    private Book book;
    @BsonProperty(value = "client", useDiscriminator = true)
    private Client client;
    @BsonCreator
    public Rent(@BsonProperty("_id") UUID entityId,
                @BsonProperty("beginTime") Date beginTime,
                @BsonProperty("endTime") Date endTime,
                @BsonProperty("book") Book book,
                @BsonProperty("client") Client client) {
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
        this.endTime = new Date();
    }
}
