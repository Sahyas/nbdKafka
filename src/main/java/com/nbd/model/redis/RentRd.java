package com.nbd.model.redis;

import com.nbd.model.mongo.BookMgd;
import com.nbd.model.mongo.ClientMgd;

import org.bson.codecs.pojo.annotations.BsonCreator;

import java.util.Date;
import java.util.UUID;

import javax.json.bind.annotation.JsonbProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
public class RentRd extends AbstractEntityRd {
    @JsonbProperty("beginTime")
    private Date beginTime;
    @JsonbProperty("endTime")
    private Date endTime;
    @JsonbProperty("book")
    private BookMgd book;
    @JsonbProperty(value = "client")
    private ClientMgd client;
    @BsonCreator
    public RentRd(@JsonbProperty("_id") UUID entityId,
                   @JsonbProperty("beginTime") Date beginTime,
                   @JsonbProperty("endTime") Date endTime,
                   @JsonbProperty("book") BookMgd book,
                   @JsonbProperty("client") ClientMgd client) {
        super(entityId);
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.book = book;
        this.client = client;
    }

    public RentRd(ClientMgd client, BookMgd book) {
        super(UUID.randomUUID());
        this.client = client;
        this.book = book;
        this.beginTime = new Date();
        this.endTime = new Date();
    }
}
