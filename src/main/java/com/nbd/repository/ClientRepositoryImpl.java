package com.nbd.repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.nbd.model.Client;
import org.bson.conversions.Bson;

public class ClientRepositoryImpl extends AbstractMongoRepository<Client> {

    public ClientRepositoryImpl() {
        super("clients", Client.class);
    }

    public Client findByPersonalID(String personalId) {
        MongoCollection<Client> collection = mongoDb.getCollection(collectionString, Client.class);
        Bson filter = Filters.eq("personalId", personalId);
        return collection
                .find()
                .filter(filter)
                .first();
    }

    public void clearDatabase() {
        MongoCollection<Client> collection = mongoDb.getCollection(collectionString, Client.class);
        collection.drop();
    }

    @Override
    public void close() throws Exception {

    }
}
