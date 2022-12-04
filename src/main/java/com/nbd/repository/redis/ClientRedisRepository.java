package com.nbd.repository.redis;

import com.nbd.model.redis.ClientRd;

public class ClientRedisRepository extends AbstractRedisRepository<ClientRd> {
    public ClientRedisRepository() {
        super(ClientRd.class, "client:");
    }
}
