package com.nbd.repository.redis;

import com.nbd.model.Rent;

public class RentRedisRepository extends AbstractRedisRepository<Rent> {
    public RentRedisRepository() {
        super(Rent.class, "rent:");
    }
}
