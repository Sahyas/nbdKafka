package com.nbd.model.mapper;

import com.nbd.model.dto.Rent;
import com.nbd.model.mongo.RentMgd;
import com.nbd.model.redis.RentRd;

public class RentMapper {
    public RentMgd rentToBsonDocument(Rent rent) {
        return RentMgd.builder()
                .id(rent.getUniqueId())
                .book(rent.getBook())
                .beginTime(rent.getBeginTime())
                .endTime(rent.getEndTime())
                .client(rent.getClient())
                .build();
    }

    public RentRd rentToJsonDocument(Rent rent) {
        return RentRd.builder()
                .id(rent.getUniqueId())
                .book(rent.getBook())
                .beginTime(rent.getBeginTime())
                .endTime(rent.getEndTime())
                .client(rent.getClient())
                .build();
    }
}
