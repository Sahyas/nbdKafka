package com.nbd.model.mongo;

import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.Date;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
public class RentMgd extends AbstractEntityMgd {
    @BsonProperty("beginTime")
    private Date beginTime;
    @BsonProperty("endTime")
    private Date endTime;
    @BsonProperty("book")
    private BookMgd book;
    @BsonProperty(value = "client", useDiscriminator = true)
    private ClientMgd client;
    @BsonCreator
    public RentMgd(@BsonProperty("_id") UUID entityId,
                   @BsonProperty("beginTime") Date beginTime,
                   @BsonProperty("endTime") Date endTime,
                   @BsonProperty("book") BookMgd book,
                   @BsonProperty("client") ClientMgd client) {
        super(entityId);
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.book = book;
        this.client = client;
    }

    public RentMgd(ClientMgd client, BookMgd book) {
        super(UUID.randomUUID());
        this.client = client;
        this.book = book;
        this.beginTime = new Date();
        this.endTime = new Date();
    }
}
