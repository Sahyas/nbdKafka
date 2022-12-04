package com.nbd.model.dto;

import com.nbd.model.mongo.BookMgd;
import com.nbd.model.mongo.ClientMgd;

import java.util.Date;
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
public class Rent extends AbstractEntity {
    private Date beginTime;
    private Date endTime;
    private BookMgd book;
    private ClientMgd client;

    public Rent(UUID uniqueId, Date beginTime, Date endTime, BookMgd book, ClientMgd client) {
        super(uniqueId);
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.book = book;
        this.client = client;
    }
}
