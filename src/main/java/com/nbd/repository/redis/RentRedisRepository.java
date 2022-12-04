package com.nbd.repository.redis;

import com.nbd.model.redis.RentRd;

public class RentRedisRepository extends AbstractRedisRepository<RentRd> {
    public RentRedisRepository() {
        super(RentRd.class, "rent:");
    }
}
