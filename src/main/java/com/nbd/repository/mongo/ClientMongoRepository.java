package com.nbd.repository.mongo;

import com.nbd.model.Client;

public class ClientMongoRepository extends AbstractMongoRepository<Client> {

    public ClientMongoRepository() {
        super("clients", Client.class);
    }
}
