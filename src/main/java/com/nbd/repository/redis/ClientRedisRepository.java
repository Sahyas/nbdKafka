package com.nbd.repository.redis;

import com.nbd.model.Client;

public class ClientRedisRepository extends AbstractRedisRepository<Client> {
    public ClientRedisRepository() {
        super(Client.class, "client:");
    }
}
