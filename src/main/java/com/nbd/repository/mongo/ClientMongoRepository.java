package com.nbd.repository.mongo;

import com.nbd.model.mongo.ClientMgd;

public class ClientMongoRepository extends AbstractMongoRepository<ClientMgd> {

    public ClientMongoRepository() {
        super("clients", ClientMgd.class);
    }
}
